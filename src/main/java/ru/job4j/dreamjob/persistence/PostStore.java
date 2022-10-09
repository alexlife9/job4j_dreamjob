package ru.job4j.dreamjob.persistence;

import org.apache.http.annotation.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

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
 * @version 7.0
 * @since 09.10.2022
 */
@ThreadSafe
@Repository
public class PostStore {

    private final AtomicInteger idS = new AtomicInteger(1); /* начальный серийный айди = 1 */

    private final Map<Integer, Post> postStore = new ConcurrentHashMap<>();

    private PostStore() {
    }

    public Collection<Post> findAll() {
        return postStore.values();
    }

    public void addPost(Post post) {
        post.setId(idS.getAndIncrement()); /* установили автоматический счетчик всех новых id */
        post.setCreated(LocalDateTime.now()); /* назначаем дату создания */
        postStore.put(post.getId(), post);
    }

    public Post findByIdPost(int id) {
        return postStore.get(id);
    }

    public void updatePost(Post post) {
        postStore.replace(post.getId(), post);
    }
}
