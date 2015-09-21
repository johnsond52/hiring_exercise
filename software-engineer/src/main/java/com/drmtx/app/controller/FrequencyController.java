package com.drmtx.app.controller;

import com.drmtx.app.Application;
import com.drmtx.app.request.FrequencyNewRequest;
import com.drmtx.app.resource.BaseResource;
import com.drmtx.app.resource.ErrorResource;
import com.drmtx.app.resource.FrequencyNewResource;
import com.drmtx.app.resource.FrequencyResource;
import com.drmtx.app.service.FrequencyService;
import com.drmtx.app.util.RedditCommentWordCounter;
import com.drmtx.app.util.exception.RedditCommunicationException;
import com.drmtx.app.util.exception.RedditDataException;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Contains the wordcount related endpoints
 * <p>
 * Created by steve on 9/17/15.
 */
@RestController
public class FrequencyController {
    private static final Logger log = LogManager.getLogger(FrequencyController.class);

    private static final String FREQ_PATH = "/frequency";
    private static final String NEW_PATH = FREQ_PATH + "/new";
    private static final String FREQ_COUNT_PATH = FREQ_PATH + "/{id}";

    @Autowired
    private FrequencyService freqService;

    @Autowired
    private RedditCommentWordCounter commentWordCounter;

    /**
     * Handles requests for a new Frequency Calculation
     *
     * @param req this contains the Reddit URL to be analyzed
     * @return a FrequencyNewResource containing the newly created id for the calculation
     * @throws Exception
     */
    @RequestMapping(value = Application.BASE_API_CTX + NEW_PATH, method = RequestMethod.POST)
    public BaseResource handleNew(@RequestBody FrequencyNewRequest req) throws Exception {
        String url = req.getUrl();
        Map<String, Long> counts = null;
        Long id = null;

        try {
            counts = commentWordCounter.getCommentWordCountMap(url);
        } catch (RedditCommunicationException e) {
            log.error("Unable to communicate with the Reddit API.", e);
            throw e;
        } catch (RedditDataException e) {
            log.error("Unable to obtain data from the Reddit API response.", e);
            throw e;
        }

        id = freqService.saveFrequencyCalculation(counts, url);
        return new FrequencyNewResource(id);
    }

    /**
     * Handles requests for the word count information
     *
     * @param id    the id of the associated FrequencyCalculation
     * @param count when not null and greated than 0, this will limit the results returned
     * @return a FrequencyResource instance with the word count data
     */
    @RequestMapping(value = Application.BASE_API_CTX + FREQ_COUNT_PATH, method = RequestMethod.GET)
    public BaseResource handleCount(@PathVariable Long id, @RequestParam(required = false) Integer count) {
        if (count != null && count < 1) {
            count = null;
        }
        return new FrequencyResource(freqService.getWordFreqienciesByCalcId(id, count));
    }

    @ExceptionHandler(RedditCommunicationException.class)
    public BaseResource handleRedditCommunicationException(HttpServletResponse resp, RedditCommunicationException e) {
        resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return new ErrorResource(resp.getStatus(), e.getMessage());
    }

    @ExceptionHandler(RedditDataException.class)
    public BaseResource handleRedditDataException(HttpServletResponse resp, RedditDataException e) {
        resp.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return new ErrorResource(resp.getStatus(), e.getMessage());
    }
}
