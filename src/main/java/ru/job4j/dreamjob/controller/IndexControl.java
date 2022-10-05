package ru.job4j.dreamjob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Что такое клиент-серверное приложение
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.10.2022
 */
@RestController
public class IndexControl {

    @GetMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}