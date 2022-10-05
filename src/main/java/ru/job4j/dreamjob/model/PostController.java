package ru.job4j.dreamjob.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Thymeleaf, Циклы
 *
 * Метод posts принимает объект Model. Он используется Thymeleaf для поиска объектов, которые нужны отобразить на Виде.
 * Контроллер заполняет Model и передает два объекта в Thymeleaf – Model и View(posts.html).
 * Thymeleaf генерирует HTML и возвращает ее клиенту.
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.10.2022
 */
@Controller
public class PostController {

    private final PostStore store = PostStore.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
    }
}
