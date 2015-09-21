package com.drmtx.app.entity;

import javax.persistence.*;

/**
 * An instance of a word, and its frequency, in the context of it's FK to the FrequencyCalculation
 * <p>
 * Created by steve on 9/20/15.
 */
@Entity
@Table(name = "WordFrequency")
public class WordFrequency extends BaseEntity {

    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "count", nullable = false)
    private long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequencyCalcId", nullable = false)
    private FrequencyCalculation frequencyCalculation;

    public WordFrequency() {
    }

    public WordFrequency(String word, Long count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public FrequencyCalculation getFrequencyCalculation() {
        return frequencyCalculation;
    }

    public void setFrequencyCalculation(FrequencyCalculation frequencyCalculation) {
        this.frequencyCalculation = frequencyCalculation;
    }
}
