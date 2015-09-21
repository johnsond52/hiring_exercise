package com.drmtx.app.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * A resource to be returned to the client when there is an error.
 * <p>
 * Created by steve on 9/19/15.
 */
public class ErrorResource extends BaseResource {

    private static final String STATUS_KEY = "httpStatus";
    private static final String MESSAGE_KEY = "message";

    private int httpStatus;
    private String message;

    public ErrorResource(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorResource(int httpStatus, String message) {
        this(httpStatus);
        this.message = message;
    }

    @Override
    public Object getContent() {
        Map<String, Object> content = new HashMap<>();

        content.put(STATUS_KEY, httpStatus);
        if (message != null) {
            content.put(MESSAGE_KEY, message);
        }

        return content;
    }
}
