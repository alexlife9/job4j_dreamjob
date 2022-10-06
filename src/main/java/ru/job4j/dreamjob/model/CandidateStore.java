package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Список Вакансий
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.10.2022
 */
public class CandidateStore {
    private static final CandidateStore CST = new CandidateStore();

    private final Map<Integer, Candidate> candidate = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidate.put(1, new Candidate(1, "Vasya", "junior", LocalDateTime.now()));
        candidate.put(2, new Candidate(2, "Petya", "middle", LocalDateTime.now()));
        candidate.put(3, new Candidate(3, "Maria", "senior", LocalDateTime.now()));
    }

    public static CandidateStore instOf() {
        return CST;
    }

    public Collection<Candidate> findAll() {
        return candidate.values();
    }
}
