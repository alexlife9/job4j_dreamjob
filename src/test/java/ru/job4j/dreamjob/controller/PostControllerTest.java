package ru.job4j.dreamjob.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тестирование с помощью Mockito
 *
 * @author Alex_life
 * @version 2.0
 * @since 24.10.2022
 */
@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    @Mock
    private PostService postService;
    @Mock
    private HttpSession httpSession;
    @Mock
    private CityService cityService;
    @Mock
    private Model model;
    @InjectMocks
    private PostController postController;
    private final City city1 = new City(1, "a");
    private final City city2 = new City(2, "b");
    private final Post post1 = new Post(1, "a post", "a", LocalDateTime.now(), city1);
    private final Post post2 = new Post(2, "b post", "b", LocalDateTime.now(), city2);
    private final User user = new User();

    @Before
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void posts() {
        user.setName("Гость");
        List<Post> input = Arrays.asList(post1, post2);
        when(httpSession.getAttribute("user")).thenReturn(null);
        when(postService.findAll()).thenReturn(input);
        String page = postController.posts(model, httpSession);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", input);
        assertThat(page, is("posts"));
    }

    @Test
    public void addPost() {
        user.setName("Гость");
        List<City> cities = Arrays.asList(city1, city2);
        when(httpSession.getAttribute("user")).thenReturn(null);
        when(postService.findByIdPost(999)).thenReturn(post1);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.addPost(model, httpSession);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", new Post());
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    @Test
    public void createPost() {
        String page = postController.createPost(post1);
        verify(postService).addPost(post1);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void formUpdatePost() {
        List<City> cities = Arrays.asList(city1, city2);
        when(postService.findByIdPost(1)).thenReturn(post1);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formUpdatePost(model, 1);
        verify(model).addAttribute("postUPD", postService.findByIdPost(1));
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("updatePost"));
    }

    @Test
    public void updatePostForCity() {
        post1.setCity(city2);
        when(cityService.findById(1)).thenReturn(city2);
        String page = postController.updatePost(post1);
        verify(postService).updatePost(post1);
        assertThat(page, is("redirect:/posts"));
    }
}