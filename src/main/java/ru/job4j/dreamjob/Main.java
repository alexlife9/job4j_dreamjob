package ru.job4j.dreamjob;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Запуск web приложения Работа мечты
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

    private Properties loadDbProperties() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        Main.class.getClassLoader()
                                .getResourceAsStream("db.properties")
                )
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return cfg;
    }

    @Bean
    public BasicDataSource loadPool() {
        Properties cfg = loadDbProperties();
        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
        return pool;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Go to http://localhost:8080/index");
    }
}
