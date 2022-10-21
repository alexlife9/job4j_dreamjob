package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.model.UserSession;
import ru.job4j.dreamjob.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Контроллер для регистрации пользователей
 *
 * @author Alex_life
 * @version 3.0
 * @since 21.10.2022
 */
@ThreadSafe
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formAddUser")
    public String formAddUser(Model model, HttpSession session) {
        model.addAttribute("user", UserSession.getUser(session));
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user, HttpSession session) {
        user.setCreated(LocalDateTime.now());
        model.addAttribute("user", UserSession.getUser(session));
        Optional<User> regUser = userService.addUser(user);
        if (regUser.isEmpty()) {
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
    public String fail(Model model, @RequestParam(name = "message", required = false) Boolean message) {
        model.addAttribute("message", message == null);
        return "failReg";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByEmailAndPassword(
                user.getEmail(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
