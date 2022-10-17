package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.persistence.PostDBStore;
import ru.job4j.dreamjob.persistence.UserDBStore;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тестирование базы данных. Liquibase H2
 *
 * @author Alex_life
 * @version 1.0
 * @since 15.10.2022
 */
public class PostDBStoreTest {
    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Java Job", "jj", LocalDateTime.now(), new City(1));
        store.addPost(post);
        Post postInDb = store.findByIdPost(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }
}
