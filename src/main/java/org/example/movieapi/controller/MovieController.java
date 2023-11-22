package org.example.movieapi.controller;

import jakarta.validation.Valid;
import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieSimple> getAllMovies(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDetail getById(@PathVariable("id") int id) {
      return movieService.findById(id)
              .orElseThrow(() -> new ResponseStatusException(
                      HttpStatus.NOT_FOUND, "movie not found"
              ));
    }

    @PostMapping
    public MovieSimple add(@Valid @RequestBody MovieCreate movie) {
        return movieService.add(movie);
    }


    @PutMapping
    public MovieSimple update(@Valid @RequestBody MovieSimple movie) {
        return movieService.update(movie)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "movie not found"
                ));
    }

    @PatchMapping("/{idMovie}/setDirector/{idDirector}")
    public MovieDetail setDirector(int idMovie, int idDirector){
        return movieService.setDirector(idMovie, idDirector)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "movie or director not found"
                ));
    }

    @DeleteMapping("{id}")
    public void delete(int id) {
        if (!movieService.delete(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "movie or director not found"
            );
        }
    }
}
