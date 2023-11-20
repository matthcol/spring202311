package org.example.movieapi.repository.tu;

import org.example.movieapi.entity.Person;
import org.example.movieapi.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class PersonRepositoryTestRead {
    @Autowired
    PersonRepository personRepository;

    // logging from sl4j
    Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

    List<Person> initialPersons;

    @BeforeEach
    void initDataDb() {
        initialPersons = List.of(
                new Person("Clint Eastwood", LocalDate.of(1930,5,31)),
                new Person("Brad Pitt", LocalDate.of(1963,12,18)),
                new Person("Leonardo DiCaprio", LocalDate.of(1974,11,11))
        );
        personRepository.saveAllAndFlush(initialPersons);
    }

    @Test
    void testFindById() {

    }

    @Test
    void testFindAll() {
        var personsRead = personRepository.findAll();
        logger.info("Persons read from database: " + personsRead);
    }
}
