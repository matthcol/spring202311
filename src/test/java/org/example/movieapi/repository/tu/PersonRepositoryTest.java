package org.example.movieapi.repository.tu;

import org.example.movieapi.entity.Person;
import org.example.movieapi.repository.PersonRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest // by default create a H2 db specific for testing: generate DDL create-drop
@ActiveProfiles("tu")
@AutoConfigureTestDatabase(replace = NONE)
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    // logging from sl4j
    Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);

    // transaction auto with rollback
    // @Rollback(false)
    @Test
    // @Order(1)
    void testSave(){
        var person = new Person("Clint Eastwood", LocalDate.of(1930,5,31));
        personRepository.save(person);
        personRepository.flush(); // force INSERT at least now
        assertNotNull(person.getId());
        // System.out.println(person);
        logger.info("Person inserted in database: " + person);
    } // rollback

    @Test
    // @Order(2)
    void testFindAll(){
        var persons = personRepository.findAll();
        assertTrue(persons.isEmpty(), "empty table person");
        //  System.out.println(persons);
        logger.info("Persons read from database: " + persons);
    }

}