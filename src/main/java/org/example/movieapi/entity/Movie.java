package org.example.movieapi.entity;

import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length=300)
    private String title;

    // nullable = false due to primitive type
    private int year;

    private Integer duration;

    @Column(length = 4000)
    private String synopsis;

    @Column(length = 300)
    private String posterUri;
}
