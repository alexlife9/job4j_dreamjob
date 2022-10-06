package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thymeleaf, Циклы
 *
 * Хранилище будет хранить добавленные из браузера вакансии
 *
 * @author Alex_life
 * @version 4.0
 * @since 06.10.2022
 */
public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        /* удалил вручную добавленные вакансии */

    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        posts.put(post.getId(), post);
    }
}
