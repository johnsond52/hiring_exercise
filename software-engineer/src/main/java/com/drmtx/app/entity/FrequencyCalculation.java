package com.drmtx.app.entity;

import javax.persistence.*;
import java.util.List;

/**
 * An instance of a word frequency calculation
 * <p>
 * Created by steve on 9/20/15.
 */
@Entity
@Table(name = "FrequencyCalc")
public class FrequencyCalculation extends BaseEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @OneToMany(mappedBy = "frequencyCalculation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordFrequency> wordFrequencies;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WordFrequency> getWordFrequencies() {
        return wordFrequencies;
    }

    public void setWordFrequencies(List<WordFrequency> wordFrequencies) {
        if (wordFrequencies != null) {
            for (WordFrequency wf : wordFrequencies) {
                wf.setFrequencyCalculation(this);
            }
        }
        this.wordFrequencies = wordFrequencies;
    }
}
