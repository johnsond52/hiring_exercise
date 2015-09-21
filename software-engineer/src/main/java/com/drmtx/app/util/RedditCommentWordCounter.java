package com.drmtx.app.util;

import com.drmtx.app.util.exception.RedditCommunicationException;
import com.drmtx.app.util.exception.RedditDataException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to fetch Reddit comments and execute a word count.
 * <p>
 * Created by steve on 9/20/15.
 */
@Component
public class RedditCommentWordCounter {

    /**
     * Given a Reddit comment thread url, this will fetch the resource, then execute a word count on the JSON
     *
     * @param commentThreadUrl the URL to the comment thread being evaluated
     * @return a Map containing the words (key) and their counts (value)
     * @throws RedditCommunicationException when there is an issue communicating with Reddit
     * @throws RedditDataException          when there is a problem with the data returned from Reddit
     */
    public Map<String, Long> getCommentWordCountMap(String commentThreadUrl) throws RedditCommunicationException, RedditDataException {
        Map<String, Long> counts = new HashMap<>();
        // get the reddit url
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(commentThreadUrl);
        HttpResponse response = null;

        try {
            response = client.execute(request);
        } catch (IOException e) {
            throw new RedditCommunicationException("Unable to obtain a valid response from the Reddit API. Please check your URL and try again.", e);
        }

        int responseCode = response.getStatusLine().getStatusCode();

        if (responseCode == 200) {
            // parse for "body" elements. type "t1"
            JsonParser parser = null;

            try {
                parser = new JsonFactory().createParser(response.getEntity().getContent());


                JsonToken token = null;
                while (!parser.isClosed()) {
                    token = parser.nextToken();

                    if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName().equals("body")) {
                        parser.nextToken();
                        String value = parser.getValueAsString();

                        // ignore punctuation "!", ",", ".", "?" and to lower case
                        value = value.replaceAll("!|,|\\.|\\?", " ").toLowerCase();

                        // split into words by whitespace
                        String[] words = value.split("\\s+");

                        // put each word into map
                        for (String word : words) {
                            Long count = counts.get(word);

                            if (count == null) {
                                counts.put(word, 1L);
                            } else {
                                counts.put(word, count + 1L);
                            }
                        }

                    }
                }
            } catch (IOException e) {
                throw new RedditDataException("Unable to parse JSON from the Reddit response. Please check the URL and try again.", e);
            }
        } else {
            throw new RedditCommunicationException("Received an unexpected response from the Reddit API.");
        }


        return counts;
    }

}
