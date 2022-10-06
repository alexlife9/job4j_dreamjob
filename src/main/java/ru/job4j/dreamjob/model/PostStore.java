package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thymeleaf, Циклы
 *
 * Когда приложение запустится, то в хранилище будут три объекта Post.
 *
 * @author Alex_life
 * @version 3.0
 * @since 06.10.2022
 */
public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "jjj", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "mjj", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "sjj", LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
