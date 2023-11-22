package org.example.movieapi.service;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<MovieSimple> findAll();

    Optional<MovieDetail> findById(int id);

    MovieSimple add(MovieCreate movie);
}
