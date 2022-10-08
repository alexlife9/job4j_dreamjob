package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище добавленных кандидатов
 *
 * @author Alex_life
 * @version 2.0
 * @since 08.10.2022
 */
public class CandidateStore {
    private static final CandidateStore CST = new CandidateStore();

    private final AtomicInteger idS = new AtomicInteger(1);

    private final Map<Integer, Candidate> candidateStore = new ConcurrentHashMap<>();

    private CandidateStore() {
    }

    public static CandidateStore instOf() {
        return CST;
    }

    public Collection<Candidate> findAll() {
        return candidateStore.values();
    }

    public void addCandidate(Candidate candidate) {
        candidate.setId(idS.getAndIncrement());
        candidate.setCreated(LocalDateTime.now());
        candidateStore.put(candidate.getId(), candidate);
    }

    public Candidate findByIdCandidate(int id) {
        return candidateStore.get(id);
    }

    public void updateCandidate(Candidate candidate) {
        candidateStore.replace(candidate.getId(), candidate);
    }
}
