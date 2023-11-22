package org.example.movieapi.controller.tu;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.dto.PersonSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.entity.Person;
import org.example.movieapi.service.MovieService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    final static String BASE_URI = "/api/movie";
    final static String BASE_URI_ID = BASE_URI + "/{id}";

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
        given(movieService.findAll())
                .willReturn(movies);

        // when: perform HTTP request
        mockMvc.perform(
                        get(BASE_URI)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                // check response (header + content)
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",
                        hasSize(movies.size()) ))
                // TODO: check each object in array ...
        ;

        // verify mock service has been called
        BDDMockito.then(movieService)
                .should()
                .findAll();


    }

    @Test
    void getByIdWhenPresent() throws Exception {
        int id = 155; // TODO: random generation
        // prepare mock service response
        var director = PersonSimple.builder()
                .name("Christopher Nolan")
                .build();
        var actors = Set.of(
                PersonSimple.builder()
                        .name("Cylian Murphy")
                        .build(),
                PersonSimple.builder()
                        .name("Emily Blunt")
                        .build()
        );
        var movie = MovieDetail.builder()
                .id(id)
                .title("Tenet")
                .year(2020)
                .duration(150)
                .director(director)
                .actors(actors)
                .build();
        when(movieService.findById(id))
                .thenReturn(Optional.of(movie));
        // perform HTTP request
        mockMvc.perform(get(BASE_URI_ID, id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title")
                        .value(movie.getTitle()))
                .andExpect(jsonPath("$.director.name")
                        .value(movie.getDirector().getName()))
                .andExpect(jsonPath("$.actors",
                        hasSize(movie.getActors().size())))
        ;
        // verify mock service has been called
        then(movieService)
                .should()
                .findById(id);
    }

    @Test
    void getByIdWhenAbsent() throws Exception {
        int id = 155; // TODO: random generation
        // prepare mock service response
        var optMovie = Optional.<MovieDetail>empty();
        when(movieService.findById(id))
                .thenReturn(optMovie);
        // perform HTTP request
        mockMvc.perform(get(BASE_URI_ID, id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                // TODO: check JSON error message
        ;
        // verify mock service has been called
        then(movieService)
                .should()
                .findById(id);
    }
}