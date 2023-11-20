package org.example.movieapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
// @Table // name, constraints, index, schema
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Transient // non persistent attribute
    @Column(nullable = false, length = 300)
    private String name;

    // nullable
    private LocalDate birthdate;

    // mandatory for JPA
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public Person(Integer id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("birthdate=" + birthdate)
                .toString();
    }
}
