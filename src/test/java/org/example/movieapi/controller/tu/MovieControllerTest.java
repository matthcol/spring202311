package org.example.movieapi.controller.tu;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    final static String BASE_URI = "/api/movie";

    // client HTTP requests
    @Autowired
    MockMvc mockMvc;

    // mock component under MovieController
    @MockBean
    MovieService movieService;

    @Test
    void getAllMovies() throws Exception {
        // prepare response from mock movie service
        var movies = List.of(
                MovieSimple.builder()
                        .id(3)
                        .title("Tenet")
                        .year(2020)
                        .duration(150)
                        .build(),
                MovieSimple.builder()
                        .id(12)
                        .title("Oppenheimer")
                        .year(2023)
                        .duration(180)
                        .build(),
                MovieSimple.builder()
                        .id(13)
                        .title("Interstellar")
                        .year(2014)
                        .duration(169)
                        .build(),
                MovieSimple.builder()
                        .id(15)
                        .title("Inception")
                        .year(2010)
                        .duration(148)
                        .build()
        );
        BDDMockito.given(movieService.findAll())
                .willReturn(movies);

        // when: perform HTTP request
        mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_URI)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print());

        // TODO: continue test


    }

    @Test
    void getByIdWhenPresent() {
    }

    @Test
    void getByIdWhenAbsent() {
    }
}