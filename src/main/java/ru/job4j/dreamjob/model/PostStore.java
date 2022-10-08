package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище вакансий
 *
 * Хранит добавленные из браузера вакансии
 *
 * @author Alex_life
 * @version 5.0
 * @since 08.10.2022
 */
public class PostStore {

    private static final PostStore INST = new PostStore();

    private final AtomicInteger idS = new AtomicInteger(1); /* начальный серийный айди = 1 */

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
        post.setId(idS.getAndIncrement()); /* установили автоматический счетчик всех новых id */
        post.setCreated(LocalDateTime.now()); /* назначаем дату создания */
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

}
