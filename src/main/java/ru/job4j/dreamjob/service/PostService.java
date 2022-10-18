package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDBStore;

import java.util.Collection;
import java.util.List;

/**
 * Сервис связывающий модель вакансий с базой данных вакансий
 *
 * @author Alex_life
 * @version 4.0
 * @since 18.10.2022
 */
@ThreadSafe
@Service
public class PostService {
    private final PostDBStore storePost;
    private final CityService cityService;

    public PostService(PostDBStore store, CityService city) {
        this.storePost = store;
        this.cityService = city;
    }

    public Collection<Post> findAll() {
        List<Post> posts = storePost.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
    }

    public void addPost(Post post) {
        storePost.addPost(post);
    }

    public Post findByIdPost(int id) {
        return storePost.findByIdPost(id);
    }

    public void updatePost(Post post) {
        storePost.updatePost(post);
    }
}
