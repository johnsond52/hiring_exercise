package com.drmtx.app.request;

/**
 * This class holds the JSON body data that is deserialized from the request. Just contains the URL.
 * <p>
 * Created by steve on 9/20/15.
 */
public class FrequencyNewRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
