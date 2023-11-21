package org.example.movieapi.repository.tu;

import jakarta.persistence.EntityManager;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.entity.Person;
import org.example.movieapi.repository.MovieRepository;
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
import java.util.stream.Stream;

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

    @Test
    void testStatByDirectorYearRange(){
        // given:
        var director1 = new Person("Christopher Nolan");
        var director2 = new Person("Clint Eastwood");
        Stream.of(director1, director2)
                .forEach(entityManager::persist);
        var movies = List.of(
                Movie.builder()
                        .title("Tenet")
                        .year(2020)
                        .duration(150)
                        .director(director1)
                        .build(),
                Movie.builder()
                        .title("Oppenheimer")
                        .year(2023)
                        .duration(180)
                        .director(director1)
                        .build(),
                Movie.builder()
                        .title("Interstellar")
                        .year(2014)
                        .duration(169)
                        .director(director1)
                        .build(),
                Movie.builder()
                        .title("Inception")
                        .year(2010)
                        .duration(148)
                        .director(director1)
                        .build(),
                Movie.builder()
                        .title("The Dark Knight")
                        .year(2008)
                        .duration(152)
                        .director(director1)
                        .build(),
                Movie.builder()
                        .title("Unforgiven")
                        .year(1992)
                        .duration(130)
                        .director(director2)
                        .build(),
                Movie.builder()
                        .title("Gran Torino")
                        .year(2008)
                        .duration(116)
                        .director(director2)
                        .build(),
                Movie.builder()
                        .title("High Plain Drifter")
                        .year(1973)
                        .duration(105)
                        .director(director2)
                        .build(),
                Movie.builder()
                        .title("The Outlaw Josey Wales")
                        .year(1976)
                        .duration(135)
                        .director(director2)
                        .build()
        );
        movies.forEach(entityManager::persist);
        entityManager.flush();
        entityManager.clear();
        // when:
        var stats = movieRepository.statsByDirectorYearRange(1975, 2020);
        logger.info("Movies read from database: " + stats.size());
        stats.forEach(stat -> logger.info(
                "director: " + stat.getDirectorName()
                + " ; count: " + stat.getMovieCount()
                + " ; total duration: " + stat.getTotalDuration()
                + " ; first year: " + stat.getFirstYear()
                + " ; last year: " + stat.getLastYear()
        ));
    }

}