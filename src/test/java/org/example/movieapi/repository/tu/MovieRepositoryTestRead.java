package org.example.movieapi.repository.tu;

import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Sql
@DataJpaTest
@ActiveProfiles("tupg")
@AutoConfigureTestDatabase(replace = NONE)
class MovieRepositoryTestRead {

    @Autowired
    MovieRepository movieRepository;

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
        logger.info("Movies read from database: " + movies);
        // TODO: check result
    }

}