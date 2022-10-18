package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Тестирование базы данных. Liquibase H2
 *
 * @author Alex_life
 * @version 1.0
 * @since 15.10.2022
 */
public class PostDBStoreTest {
    private static PostDBStore postStore;

    @BeforeEach
    void before() throws SQLException {
        new Main().loadPool().getConnection().prepareStatement("delete from post").execute();
        postStore = new PostDBStore(new Main().loadPool());
    }

    @Test
    void findAll() {
        Post post1 = new Post(1, "a", "a", LocalDateTime.now(), new City(3, "a"));
        Post post2 = new Post(2, "b", "b", LocalDateTime.now(), new City(4, "b"));
        postStore.addPost(post1);
        postStore.addPost(post2);
        List<Post> all = postStore.findAll();
        Assertions.assertEquals(2, all.size());
    }
}
