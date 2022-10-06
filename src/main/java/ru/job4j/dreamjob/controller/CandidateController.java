package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.CandidateStore;

import java.time.LocalDateTime;

/**
 * Список Вакансий
 *
 * После запуска программы открываем браузер по ссылке http:\\localhost:8080/candidates
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.10.2022
 */
@Controller
public class CandidateController {

    private final CandidateStore store = CandidateStore.instOf();

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", store.findAll());
        return "candidates";
    }

    @GetMapping("/formAddCandidate")
    public String addPost(Model model) {
        model.addAttribute("candidates",
                new Candidate(0, "Заполните поле", "Заполните поле", LocalDateTime.now()));
        return "AddCandidate";
    }
}
