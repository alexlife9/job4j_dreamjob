package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.PostService;

import java.time.LocalDateTime;

/**
 * Контроллер для вакансий
 *
 * Метод posts принимает объект Model. Он используется Thymeleaf для поиска объектов, которые нужны отобразить на View.
 * Контроллер заполняет Model и передает два объекта в Thymeleaf – Model и View(posts.html).
 * Thymeleaf генерирует HTML и возвращает ее клиенту.
 *
 * @author Alex_life
 * @version 4.0
 * @since 08.10.2022
 */
@Controller
public class PostController {
    private final PostService postService = PostService.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model) {
        model.addAttribute("posts",
                new Post(0, "Заполните поле", "Заполните поле", LocalDateTime.now()));
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        postService.addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("postUPD", postService.findByIdPost(id));
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        postService.updatePost(post);
        return "redirect:/posts";
    }
}
