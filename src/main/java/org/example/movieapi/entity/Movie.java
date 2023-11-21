package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

// JPA
@NamedEntityGraph(
        name="Movie.directorAndActors",
        attributeNodes = {
                @NamedAttributeNode("director"),
                @NamedAttributeNode("actors")
        }
)
@Entity
// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of={"id", "title", "year"})
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

    @ManyToOne(fetch = FetchType.LAZY) // by default eager
    @JoinColumn(name = "director_id")
    private Person director;

    @ManyToMany // Lazy by default
    @JoinTable(
            name="play",
            inverseJoinColumns = @JoinColumn(name="actor_id"), // to other entity
            joinColumns = @JoinColumn(name="movie_id") // to this entity
    )
    private Set<Person> actors;
}
