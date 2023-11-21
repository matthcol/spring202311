package org.example.movieapi.repository.tu;

import jakarta.persistence.EntityManager;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.entity.Person;
import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Sql
@DataJpaTest
@ActiveProfiles("tupg")
@AutoConfigureTestDatabase(replace = NONE)
class MovieRepositoryTestRead {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    EntityManager entityManager;

    Logger logger = LoggerFactory.getLogger(MovieRepositoryTestRead.class);

    // @Rollback(false) // for debug purpose
    @Test
    void testFindByTitle() {
        var movies = movieRepository.findByTitle("Barbie");
        logger.info("Movies read from database: " + movies);
        // TODO: check result
    }

    @Test
    void testFindByTitleYear() {
        var movies = movieRepository.findByTitleContainingIgnoreCaseAndYearBetween("avengers", 2010, 1015);
        logger.info("Movies read from database: " + movies);
        // TODO: check result
    }

    @Test
    void testFindByDirectorName(){
        var movies = movieRepository.findByDirector("Whedon");
        logger.info("Movies read from database: " + movies.size());
        movies.forEach(movie -> logger.info(movie + " directed by " + movie.getDirector()));
        // TODO: check result
    }

    @Test
    void testFindById() {
        // hypothesis: insert data before reading it
        var director = new Person("Christopher Nolan");
        var actors = Set.of(
                new Person("Cylian Murphy"),
                new Person("Emily Blunt")
        );
        entityManager.persist(director);
        actors.forEach(entityManager::persist);
        var movie = Movie.builder()
                .title("Oppenheimer")
                .year(2023)
                .director(director)
                .actors(actors)
                .build();
        entityManager.persist(movie);
        entityManager.flush();
        int idMovie = movie.getId();
        entityManager.flush();
        entityManager.clear();

        // call MovieRepository
        var optMovieRead = movieRepository.findById(idMovie);
        assertTrue(optMovieRead.isPresent(), "movie is present in db");
        optMovieRead.ifPresent(movieRead -> {
            logger.info("movie read: " + movieRead);
            logger.info("director: " + movieRead.getDirector());
            logger.info("actors: " + movieRead.getActors());
        });

    }

}