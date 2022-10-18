package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Контроллер для регистрации пользователей
 *
 * @author Alex_life
 * @version 1.0
 * @since 18.10.2022
 */
@ThreadSafe
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formAddUser")
    public String formAddUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = userService.addUser(user);
        user.setCreated(LocalDateTime.now());
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь уже существует");
            return "redirect:/fail";
        }
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("user", new User());
        return "successReg";
    }

    @GetMapping("/fail")
    public String fail(Model model) {
        return "failReg";
    }

}
