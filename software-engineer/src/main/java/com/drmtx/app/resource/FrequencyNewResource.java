package com.drmtx.app.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * A resource to be returned to the client when posting a new FrequencyCalculation. This just contains the id
 * <p>
 * Created by steve on 9/19/15.
 */
public class FrequencyNewResource extends BaseResource {

    private Long id;

    public FrequencyNewResource(Long id) {
        this.id = id;
    }

    @Override
    public Object getContent() {
        Map<String, Long> content = new HashMap<>();

        content.put("id", id);

        return content;
    }

}
