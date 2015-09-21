package com.drmtx.app.service;

import com.drmtx.app.dao.FrequencyCalculationDAO;
import com.drmtx.app.entity.FrequencyCalculation;
import com.drmtx.app.entity.WordFrequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service facade facilitating persistance for the controllers.
 * <p>
 * Created by steve on 9/20/15.
 */
@Service
public class FrequencyService {

    @Autowired
    private FrequencyCalculationDAO freqCalcDAO;

    /**
     * Save a frequency calculation and its word counts
     *
     * @param counts    a Map of words (key) and their counts (value)
     * @param redditUrl the Reddit URL that was called to retrieve this data
     * @return the id that can be used to retrieve this particular frequency calculation
     */
    public long saveFrequencyCalculation(Map<String, Long> counts, String redditUrl) {
        FrequencyCalculation fc = new FrequencyCalculation();
        fc.setUrl(redditUrl);

        List<WordFrequency> wordFreqs = new ArrayList<>();

        if (counts != null) {
            for (Map.Entry<String, Long> e : counts.entrySet()) {
                wordFreqs.add(new WordFrequency(e.getKey(), e.getValue()));
            }
        }

        fc.setWordFrequencies(wordFreqs);

        return freqCalcDAO.persist(fc);
    }

    /**
     * Given a FrequencyCalculation id, this will retrieve all of the associated word counts
     *
     * @param freqCalcId the id of the Frequency calculation
     * @param count      when present and not null, this will limit the results returned
     * @return A list of WordFrequency objects for the given FrequencyCalculation id
     */
    public List<WordFrequency> getWordFreqienciesByCalcId(Long freqCalcId, Integer count) {
        return freqCalcDAO.findWordFrequenciesByCalcId(freqCalcId, count);
    }

}
