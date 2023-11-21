package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

// JPA
@NamedQuery(
        name="Movie.statsByDirectorYearRange",
        query="""
            select 
                d.id as directorId,
                d.name as directorName,
                d.birthdate as directorBirthdate, 
                count(m.id) as movieCount, 
                coalesce(sum(m.duration),0) as totalDuration, 
                min(m.year) as firstYear, 
                max(m.year) as lastYear
            from Movie m join m.director d
            where m.year between :year1 and :year2
            group by d.id, d.name, d.birthdate
            """
)
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
    @Builder.Default
    private Set<Person> actors = new HashSet<>();
}
