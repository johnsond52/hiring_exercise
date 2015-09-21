package com.drmtx.app.dao;

import com.drmtx.app.entity.FrequencyCalculation;
import com.drmtx.app.entity.WordFrequency;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * This DAO exposes the required data layer functionality for the current version of the API
 * <p>
 * Created by steve on 9/20/15.
 */
@Repository
@Transactional
public class FrequencyCalculationDAO {
    @PersistenceContext
    private EntityManager em;

    /**
     * Persist a new FrequencyCalculation and return its id
     *
     * @param fc the FrequencyCalculation to persist
     * @return the id for the newly persisted entity
     */
    public Long persist(FrequencyCalculation fc) {
        em.persist(fc);
        em.flush();
        return fc.getId();
    }

    /**
     * Get a List of WordFrequency objects by the parent FrequencyCalculation id
     *
     * @param calcId     the id of the associated FrequencyCalculation
     * @param maxResults if not null, sets a limit on how many records are returned
     * @return a List of WordFrequency objects for the associated id
     */
    public List<WordFrequency> findWordFrequenciesByCalcId(Long calcId, Integer maxResults) {
        List<WordFrequency> results = null;
        Query q = em.createQuery("SELECT wf FROM WordFrequency wf WHERE wf.frequencyCalculation.id = ?1 ORDER BY wf.count DESC");
        q.setParameter(1, calcId);
        if (maxResults != null) {
            q.setMaxResults(maxResults);
        }
        results = q.getResultList();
        return results;
    }
}
