package com.drmtx.app.resource;

import com.drmtx.app.Application;

/**
 * A base resource class to hold similar functionality used by resources
 * <p>
 * Created by steve on 9/18/15.
 */
public abstract class BaseResource {

    /**
     * @return the current version of the api
     */
    public String getApiVersion() {
        return Application.API_VERSION;
    }

    /**
     * Get the content specific to the resource response implementation
     *
     * @return the resource content
     */
    public abstract Object getContent();

}
