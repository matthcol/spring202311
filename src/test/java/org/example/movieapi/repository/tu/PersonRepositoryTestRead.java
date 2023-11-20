package org.example.movieapi.repository.tu;

import jakarta.persistence.EntityManager;
import org.example.movieapi.entity.Person;
import org.example.movieapi.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

@DataJpaTest
@ActiveProfiles("tu")
@AutoConfigureTestDatabase(replace = NONE)
public class PersonRepositoryTestRead {
    @Autowired
    PersonRepository personRepository;

    // Hibernate entityManager used by personRepository
    // as EntityManager (JPA) or TestEntityManager (Spring)
    @Autowired
    EntityManager entityManager;

    // logging from sl4j
    Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

    List<Person> initialPersons;

    // included in transaction (rollback by default)
    @BeforeEach
    void initDataDb() {
        initialPersons = List.of(
                new Person("Clint Eastwood", LocalDate.of(1930,5,31)),
                new Person("Brad Pitt", LocalDate.of(1963,12,18)),
                new Person("Leonardo DiCaprio", LocalDate.of(1974,11,11))
        );
        // SQL: 3 x INSERT
        personRepository.saveAllAndFlush(initialPersons);
        // empty Hibernate cache
        entityManager.clear();
    }

    @Test
    void testFindByIdWhenNotPresent() {
        int idPerson = 0;

        // SQL: select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0 where p1_0.id=?
        var optPersonRead = personRepository.findById(idPerson);
        logger.info("Person read from database (opt): " + optPersonRead);

        assertTrue(optPersonRead.isEmpty(), "person not present in db");
    }

    @Test
    void testFindByIdWhenPresent() {
        int idPerson = initialPersons.get(0).getId();

        // SQL: select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0 where p1_0.id=?
        var optPersonRead = personRepository.findById(idPerson);
        logger.info("Person read from database (opt): " + optPersonRead);

        assertTrue(optPersonRead.isPresent(), "person present in db");

        // var personRead = optPersonRead.get();
        optPersonRead.ifPresent(personRead -> {
            assertAll(
                    () -> assertEquals(idPerson, personRead.getId(), "id"),
                    () -> assertEquals("Clint Eastwood", personRead.getName(), "name")
                    // ...
            );
        });
    }

    @Test
    void testFindAll() {
        // SQL: select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0
        var personsRead = personRepository.findAll();
        logger.info("Persons read from database: " + personsRead);
        // TODO: check 3 results in any order
    }

    // Sort, Pageable, Example with findALl

    static Stream<Sort> sortSource(){
        return Stream.of(
                // select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0 order by p1_0.name
                Sort.by("name")
                // name desc
                // select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0 order by p1_0.name desc
                , Sort.by(desc("name"))
                // birthdate + name
                // select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0 order by p1_0.birthdate,p1_0.name
                , Sort.by("birthdate", "name")
                // birthdate desc, name asc
                // select p1_0.id,p1_0.birthdate,p1_0.name from person p1_0 order by p1_0.birthdate desc,p1_0.name
                , Sort.by(desc("birthdate"), asc("name"))
        );
    }

    @Sql
    @ParameterizedTest
    @MethodSource("sortSource")
    void testFindAllSorted(Sort sort){
        var personsRead = personRepository.findAll(sort);
        logger.info("Persons read from database: " + personsRead);
        // TODO: check 3 results in this order
    }

    static Stream<Example<Person>> examplePersonSource(){
        var matcherCaseInsensitiveEnding = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);
        return Stream.of(
                Example.of(new Person("Clint Eastwood")),
                Example.of(new Person("eastwood"), matcherCaseInsensitiveEnding)
        );
    }
    @ParameterizedTest
    @MethodSource("examplePersonSource")
    void testFindByExample(Example<Person> example) {
        var personsRead = personRepository.findAll(example);
        logger.info("Persons read from database: " + personsRead);
        // TODO: check results
    }
}
