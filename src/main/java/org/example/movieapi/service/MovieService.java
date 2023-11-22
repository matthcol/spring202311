package org.example.movieapi.service;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<MovieSimple> findAll();

    /**
     *
     * @param id
     * @return Optional empty if not found
     */
    Optional<MovieDetail> findById(int id);

    MovieSimple add(MovieCreate movie);

    /**
     *
     * @param movie
     * @return Optional empty if not found
     */
    Optional<MovieSimple> update(MovieSimple movie);

    /**
     *
     * @param idMovie
     * @param idDirector
     * @return Optional empty if movie or director not found
     */
    Optional<MovieDetail> setDirector(int idMovie, int idDirector);

    /**
     *
     * @param id
     * @return false if not found
     */
    boolean delete(int id);
}
