package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище добавленных вакансий
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

    private final Map<Integer, Post> postStore = new ConcurrentHashMap<>();

    private PostStore() {
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return postStore.values();
    }

    public void add(Post post) {
        post.setId(idS.getAndIncrement()); /* установили автоматический счетчик всех новых id */
        post.setCreated(LocalDateTime.now()); /* назначаем дату создания */
        postStore.put(post.getId(), post);
    }

    public Post findById(int id) {
        return postStore.get(id);
    }

    public void update(Post post) {
        postStore.replace(post.getId(), post);
    }
}
