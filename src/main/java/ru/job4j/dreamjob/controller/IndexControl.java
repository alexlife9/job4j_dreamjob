package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.model.UserSession;

import javax.servlet.http.HttpSession;

/**
 * Главная страница
 *
 * Передаем на клиент(браузер в данном случае) текст в виде HTML.
 * Браузер использует язык HTML для отображения содержимого страницы.
 * В Java есть несколько способов сгенерировать HTML код – JSP, Velocity, Thymeleaf.
 * Схема работы веб приложения:
 * - Браузер отправляет запрос на сервер. Spring boot отдает запрос в контроллер.
 * - Контроллер ищет и заполняет вид, а потом отдает его в виде html клиенту.
 * - HTML – это текст. Браузер его преобразует в пользовательский интерфейс.
 *
 * Запускаем приложение и открываем ссылку http:\\localhost:8080/index
 * Браузер отправляет запрос на сервер. Сервер ищет нужный контроллер, который в свою очередь берет файл index.html
 * и возвращает его содержимое клиенту.
 *
 * @author Alex_life
 * @version 5.0
 * @since 21.10.2022
 */
@ThreadSafe
@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("user", UserSession.getUser(session));
        return "index";
    }
}