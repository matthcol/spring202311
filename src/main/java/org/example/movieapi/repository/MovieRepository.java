package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    // Query auto generated
    List<Movie> findByTitle(String title);

    // Error: Error creating bean with name 'movieRepository
    // .. No property 'name' found for type 'Movie'
    // List<Movie> findByName(String title);
}
