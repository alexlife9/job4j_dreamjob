package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateDBStore;

import java.util.Collection;
import java.util.List;

/**
 * Сервис связывающий модель кандидатов с базой данных кандидатов
 *
 * @author Alex_life
 * @version 4.0
 * @since 16.10.2022
 */
@ThreadSafe
@Service
public class CandidateService {
    private final CandidateDBStore storeCandidate;
    private final CityService cityService;

    public CandidateService(CandidateDBStore store, CityService city) {
        this.storeCandidate = store;
        this.cityService = city;
    }

    public Collection<Candidate> findAll() {
        List<Candidate> candidates = storeCandidate.findAll();
        candidates.forEach(
                candidate -> candidate.setCity(
                        cityService.findById(candidate.getCity().getId())
                )
        );
        return candidates;
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
