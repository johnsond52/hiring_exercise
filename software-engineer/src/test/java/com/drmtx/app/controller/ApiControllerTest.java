package com.drmtx.app.controller;

import com.drmtx.app.Application;
import com.drmtx.app.resource.ApiResource;
import com.drmtx.app.resource.BaseResource;
import com.drmtx.app.resource.ErrorResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by steve on 9/20/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApiControllerTest {

    @Autowired
    private ApiController apiController;

    @Test
    public void testHandleApi() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("http://fake.request.test");
        BaseResource result = apiController.handleApi(req);

        assertTrue(result instanceof ApiResource);
    }

    @Test
    public void testHandleError() {
        MockHttpServletResponse resp = new MockHttpServletResponse();
        resp.setStatus(309);
        BaseResource result = apiController.handleError(resp);

        assertTrue(result instanceof ErrorResource);
    }

}
