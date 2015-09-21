package com.drmtx.app.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This resource returns some basic information about the API to the client.
 * <p>
 * Created by steve on 9/20/15.
 */
public class ApiResource extends BaseResource {

    private List<String> endpoints;

    public ApiResource(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public Object getContent() {
        Map<String, Object> content = new HashMap<>();

        content.put("message", "This is the root of the hiring exercise API and a list of the endpoints available.");
        content.put("endpoints", endpoints);

        return content;
    }
}
