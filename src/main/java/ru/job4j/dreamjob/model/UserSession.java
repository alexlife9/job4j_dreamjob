package ru.job4j.dreamjob.model;

import javax.servlet.http.HttpSession;

/**
 * Привязка сессии к юзеру
 *
 * @author Alex_life
 * @version 1.0
 * @since 21.10.2022
 */
public class UserSession {
    public static User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        return user;
    }
}
