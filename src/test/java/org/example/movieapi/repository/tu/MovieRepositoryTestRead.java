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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Sql
@DataJpaTest
@ActiveProfiles("tu")
@AutoConfigureTestDatabase(replace = NONE)
class MovieRepositoryTestRead {

    @Autowired
    MovieRepository movieRepository;

    Logger logger = LoggerFactory.getLogger(MovieRepositoryTestRead.class);

    @Test
    void testFindByTitle() {
        var movies = movieRepository.findByTitle("Barbie");
        logger.info("Movies read from database: " + movies);
    }

}