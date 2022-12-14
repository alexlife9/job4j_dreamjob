package ru.job4j.dreamjob.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CandidateService;
import ru.job4j.dreamjob.service.CityService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * @author Alex_life
 * @version 3.0
 * @since 27.10.2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CandidateControllerTest {
    @Mock
    private CandidateService candidateService;
    @Mock
    private CityService cityService;
    @Mock
    private Model model;
    @Mock
    private MultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "hw".getBytes());
    @InjectMocks
    private CandidateController candidateController;
    private final City city1 = new City(1, "a");
    private final City city2 = new City(2, "b");
    private final Candidate candidate1 = new Candidate(1, "a candidate", "a", LocalDateTime.now(), city1, new byte[0]);
    private final Candidate candidate2 = new Candidate(2, "b candidate", "b", LocalDateTime.now(), city2, new byte[0]);
    private final User user = new User();

    @Before
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void candidates() {
        user.setName("??????????");
        List<Candidate> input = Arrays.asList(candidate1, candidate2);
        when(candidateService.findAll()).thenReturn(input);
        String page = candidateController.candidates(model);
        verify(model).addAttribute("candidates", input);
        assertThat(page, is("candidates"));
    }

    @Test
    void addCandidate() {
        user.setName("??????????");
        List<City> cities = Arrays.asList(city1, city2);
        when(candidateService.findByIdCandidate(8)).thenReturn(candidate1);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = candidateController.addCandidate(model);
        verify(model).addAttribute("candidates", new Candidate());
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addCandidate"));
    }

    @Test
    void createCandidate() throws IOException {
        candidate1.getPhoto();
        String page = candidateController.createCandidate(candidate1, file);
        verify(candidateService).addCandidate(candidate1);
        assertThat(page, is("redirect:/candidates"));
    }

    @Test
    void formUpdateCandidate() {
        List<City> cities = Arrays.asList(city1, city2);
        when(candidateService.findByIdCandidate(1)).thenReturn(candidate1);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = candidateController.formUpdateCandidate(model, 1);
        verify(model).addAttribute("candidateUPD", candidateService.findByIdCandidate(1));
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("updateCandidates"));
    }

    @Test
    void updateCandidate() throws IOException {
        candidate1.setCity(city2);
        when(cityService.findById(1)).thenReturn(city2);
        String page = candidateController.updateCandidate(candidate1, file);
        verify(candidateService).updateCandidate(candidate1);
        assertThat(page, is("redirect:/candidates"));
    }
}