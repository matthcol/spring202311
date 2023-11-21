package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Override
    @EntityGraph("Movie.directorAndActors")
    Optional<Movie> findById(Integer id);

    // NB: return type
    // 0..1 : Optional<T>, T
    // * : List<T>, Set<T>, Stream<T>

    // spring vocabulary to generate SQL automatically
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    // Query auto generated
    List<Movie> findByTitle(String title);

    // Error: Error creating bean with name 'movieRepository
    // .. No property 'name' found for type 'Movie'
    // List<Movie> findByName(String title);

    // liste des films dont le titre contient un mot (case insensitive)
    // et une année comprise entre 2 années en paramètres
    List<Movie> findByTitleContainingIgnoreCaseAndYearBetween(String titlePart, int year1, int year2);

    // JPQL/HQL query
    // params: ?1 ?2 ... or :name, :year with/without @Param
    @Query("select m from Movie m join fetch m.director d where d.name like %:name")
    List<Movie> findByDirector(String name);
}
