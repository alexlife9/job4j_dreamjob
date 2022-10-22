package ru.job4j.dreamjob.controller;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование с помощью Mockito
 *
 * @author Alex_life
 * @version 1.0
 * @since 21.10.2022
 */
public class PostControllerTest {
    @Test
    public void whenPosts() {
        List<Post> input = Arrays.asList(
                new Post(1, "a post", "a", LocalDateTime.now(), new City(1, "a")),
                new Post(2, "b post", "b", LocalDateTime.now(), new City(2, "b"))
        );
        PostService postService = mock(PostService.class);
        HttpSession httpSession = mock(HttpSession.class);
        CityService cityService = mock(CityService.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setName("Гость");
        when(postService.findAll()).thenReturn(input);
        PostController postController = new PostController(postService, cityService);
        String page = postController.posts(model, httpSession);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", input);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "a post", "a", LocalDateTime.now(), new City(1, "a"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.createPost(input);
        verify(postService).addPost(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormAddPost() {
        Post input = new Post(1, "a post", "a", LocalDateTime.now(), new City(1, "a"));
        input.setCity(new City(0, "c"));
        PostService postService = mock(PostService.class);
        HttpSession httpSession = mock(HttpSession.class);
        CityService cityService = mock(CityService.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setName("Гость");
        PostController postController = new PostController(postService, cityService);
        String page = postController.addPost(model, httpSession);
        verify(postService).addPost(input);
        verify(model).addAttribute("user", user);
        //verify(cityService).getAllCities();
        assertThat(page, is("addPost"));
    }


    @Test
    public void formAddPost() {
        Post input = new Post(1, "a post", "a", LocalDateTime.now(), new City(1, "a"));
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        Model model = mock(Model.class);
        when(httpSession.getAttribute("user")).thenReturn(null);
        PostController postController = new PostController(postService, cityService);
        User user = new User();
        user.setName("Гость");
        List<City> cities = Arrays.asList(new City(0, "c"), new City(1, "d"));
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.addPost(model, httpSession);
        //verify(cityService).getAllCities();
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", input);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    @Test
    public void updatePostId() {
        Post input = new Post(1, "a post", "a", LocalDateTime.now(), new City(1, "a"));
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(httpSession.getAttribute("user")).thenReturn(null);
        PostController postController = new PostController(postService, cityService);
        City city = new City(0, "c");
        input.setCity(city);
        when(cityService.findById(0)).thenReturn(city);
        String page = postController.updatePost(input);
        verify(postService).updatePost(input);
        assertThat(page, is("updatePost"));
    }

    @Test
    public void updatePost() {
        Post input = new Post(1, "a post", "a", LocalDateTime.now(), new City(1, "a"));
        HttpSession httpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(httpSession.getAttribute("user")).thenReturn(null);
        PostController postController = new PostController(postService, cityService);
        City city = new City(0, "c");
        input.setCity(city);
        when(cityService.findById(0)).thenReturn(city);
        String page = postController.updatePost(input);
        verify(postService).updatePost(input);
        assertThat(page, is("redirect:/posts"));
    }
}