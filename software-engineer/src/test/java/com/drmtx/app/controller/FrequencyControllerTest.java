package com.drmtx.app.controller;

import com.drmtx.app.Application;
import com.drmtx.app.request.FrequencyNewRequest;
import com.drmtx.app.resource.BaseResource;
import com.drmtx.app.resource.FrequencyNewResource;
import com.drmtx.app.resource.FrequencyResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by steve on 9/21/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FrequencyControllerTest {

    @Autowired
    private FrequencyController frequencyController;

    @Test
    public void handleNew() {
        FrequencyNewRequest req = new FrequencyNewRequest();
        req.setUrl("https://www.reddit.com/r/java/comments/32pj67/java_reference_in_gta_v_beautiful/.json");
        BaseResource result = null;
        try {
            result = frequencyController.handleNew(req);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(e == null);
        }

        assertTrue(result instanceof FrequencyNewResource);
    }

    @Test
    public void handleCount() {
        BaseResource result = null;
        // make sure we have at least one record to deal with - a better test infrastructure would solve this
        handleNew();

        result = frequencyController.handleCount(1L, null);

        assertTrue(result instanceof FrequencyResource);
    }
}
