package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Thymeleaf, Циклы
 *
 * Затронем идею шаблона проектирования MVC (Model, View, Control).
 * Весь пользовательский интерфейс можно разбить на отдельные экраны или виды.
 * Вид – это статический элемент программы. Он отображает формы и элементы.
 * Самым важным элементом программы являются данные. Данные отображаются в виде.
 * Чтобы данные залезли в вид, ими должен управлять контроллер. Он знает, какие данные засунуть в вид.
 *
 * View – это HTML.
 * Model – это данные нашего приложения.
 * Controller - связующее звено между видом и моделью.
 *
 * Класс Post описывает вакансии.
 * Класс PostStore описывает хранилище.
 * Post и PostStore - это модели данных.
 * файл posts.html описывает Вид.
 *
 * посмотреть результат - http:\\localhost:8080/posts
 *
 *
 * @author Alex_life
 * @version 2.0
 * @since 06.10.2022
 */
public class Post {
    private int id;
    private String name;
    private String description;
    private LocalDateTime created;

    public Post() {
    }

    public Post(int id, String name, String description, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}
