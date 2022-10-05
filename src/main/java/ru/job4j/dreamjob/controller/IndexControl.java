package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Генератор HTML
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
 * Файл index.html находится по пути: src/main/resources/templates/index.html
 *
 * @author Alex_life
 * @version 2.0
 * @since 06.10.2022
 */
@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}