package org.example.movieapi.controller;

import org.example.movieapi.entity.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @GetMapping
    public List<Movie> getAllMovies(){
        return List.of(
                Movie.builder()
                        .title("The Dark Knight")
                        .year(2008)
                        .duration(152)
                        .build(),
                Movie.builder()
                        .title("Unforgiven")
                        .year(1992)
                        .duration(130)
                        .build(),
                Movie.builder()
                        .title("Gran Torino")
                        .year(2008)
                        .duration(116)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id){
        return  Movie.builder()
                .id(id)
                .title("Gran Torino")
                .year(2008)
                .duration(116)
                .build();
    }
}
