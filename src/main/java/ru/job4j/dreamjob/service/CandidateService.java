package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateStore;

import java.util.Collection;

/**
 * Слоеная архитектура
 *
 * @author Alex_life
 * @version 3.0
 * @since 09.10.2022
 */
@ThreadSafe
@Service
public class CandidateService {
    private final CandidateStore storeCandidate;
    private final CityService cityService;

    public CandidateService(CandidateStore store, CityService city) {
        this.storeCandidate = store;
        this.cityService = city;
    }

    public Collection<Candidate> findAll() {
        return storeCandidate.findAll();
    }

    public void addCandidate(Candidate candidate) {
        storeCandidate.addCandidate(candidate);
    }

    public Candidate findByIdCandidate(int id) {
        return storeCandidate.findByIdCandidate(id);
    }

    public void updateCandidate(Candidate candidate) {
        storeCandidate.updateCandidate(candidate);
    }
}
