package com.drmtx.app.controller;

import com.drmtx.app.Application;
import com.drmtx.app.resource.ApiResource;
import com.drmtx.app.resource.BaseResource;
import com.drmtx.app.resource.ErrorResource;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle calls to the base API context and basic errors
 * <p>
 * Created by steve on 9/19/15.
 */
@RestController
public class ApiController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    private static final String[] ENDPOINTS = {"frequency/new", "frequency/{id}"};

    /**
     * Handles requests to the root of the API
     *
     * @param req Used to build a context specific set of endpoints
     * @return an ApiResource instance with basic information about this API's endpoints
     */
    @RequestMapping(Application.BASE_API_CTX)
    public BaseResource handleApi(HttpServletRequest req) {
        String baseUrl = req.getRequestURL().toString();
        List<String> endpoints = new ArrayList<>();
        for (String ep : ENDPOINTS) {
            endpoints.add(baseUrl + ep);
        }

        return new ApiResource(endpoints);
    }

    /**
     * Handles generic errors
     *
     * @param resp
     * @return an ErrorResource instance with information about the problem
     */
    @RequestMapping(ERROR_PATH)
    public BaseResource handleError(HttpServletResponse resp) {
        return new ErrorResource(resp.getStatus(), "We're sorry, there was an error with your request. Please contact support for assistance.");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
