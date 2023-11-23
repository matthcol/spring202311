package org.example.movieapi.controller;

import jakarta.validation.Valid;
import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    // @Qualifier("movieServiceJpaAlt") // choose service impl
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

    // methode with query param: name, required, defaultValue
    @GetMapping("/search")
    public List<MovieSimple> search(
            @RequestParam(name = "t", required = false) String title,
            @RequestParam(name = "y1", required = false) Integer year1,
            @RequestParam(name = "y2", required = false) Integer year2
    ){
        logger.debug("search with params: title={}, year1={}, year2={}",
                title, year1, year2);
        // TODO: add in service and call it
        return List.of();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(int id) {
        if (!movieService.delete(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "movie or director not found"
            );
        }
    }
}
