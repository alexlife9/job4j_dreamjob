package ru.job4j.dreamjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Что такое клиент-серверное приложение
 *
 * Серверное приложение - это две программы, которые общаются через протокол.
 * Клиент для web приложений - это браузер.
 * Сервер - это программа, которая может обрабатывать http запросы. Популярные серверы для Java – Tomcat, Jetty.
 * Экосистема Java с каждый годом эволюционирует. Сообщество разрабатывает удобные инструменты для быстрой разработки.
 *
 * Рассмотрим использование фреймворка Spring boot.
 * В Spring boot сразу входит сервер, что избавляет от этапа настройки сервера.
 *
 * После запуска программы открываем браузер по ссылке http:\\localhost:8080/index и видим: Greetings from Spring Boot!
 *
 * @author Alex_life
 * @version 1.0
 * @since 06.10.2022
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
