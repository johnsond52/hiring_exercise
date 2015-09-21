package com.drmtx.app.resource;

import com.drmtx.app.entity.WordFrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A resource to be returned to API clients for frequency calls
 * <p>
 * Created by steve on 9/19/15.
 */
public class FrequencyResource extends BaseResource {

    private List<WordFrequency> freqs;

    public FrequencyResource(List<WordFrequency> freqs) {
        this.freqs = freqs;
    }

    @Override
    public Object getContent() {
        return getPresentation();
    }

    /**
     * A convenience method to format the content as desired.
     *
     * @return formatted content
     */
    private List<Map<String, Object>> getPresentation() {
        List<Map<String, Object>> content = null;
        if (this.freqs != null) {
            content = new ArrayList<>();
            for (WordFrequency wf : freqs) {
                Map m = new HashMap<>();
                m.put("word", wf.getWord());
                m.put("count", wf.getCount());
                content.add(m);
            }
        }
        return content;
    }
}
