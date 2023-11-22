package org.example.movieapi.service.impl;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.repository.PersonRepository;
import org.example.movieapi.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Article: https://gayerie.dev/docs/spring/spring/spring_tx.html
@Transactional
@Service
public class MovieServiceJpa implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;
    // https://modelmapper.org/user-manual/

    @Transactional(readOnly = true)
    @Override
    public List<MovieSimple> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieEntity -> modelMapper.map(movieEntity, MovieSimple.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<MovieDetail> findById(int id) {
        return movieRepository.findById(id)
                .map(movieEntity -> modelMapper.map(movieEntity, MovieDetail.class));
    }

    @Override
    public MovieSimple add(MovieCreate movie) {
        Movie movieEntity = modelMapper.map(movie, Movie.class);
        movieRepository.saveAndFlush(movieEntity); // SQL: insert now
        return modelMapper.map(
                movieEntity,
                MovieSimple.class
        );
    } // COMMIT: valid transaction here if no exception else ROLLBACK

    @Override
    public Optional<MovieSimple> update(MovieSimple movie) {
        return  movieRepository.findById(movie.getId())
            .map(movieEntity -> {
                modelMapper.map(movie, movieEntity);
                movieRepository.saveAndFlush(movieEntity);
                return modelMapper.map(movieEntity, MovieSimple.class);
            });
    }

    @Override
    public Optional<MovieDetail> setDirector(int idMovie, int idDirector) {
        return movieRepository.findById(idMovie)
                .flatMap(movieEntity -> personRepository.findById(idDirector)
                        .map(directorEntity -> {
                            movieEntity.setDirector(directorEntity);
                            movieRepository.saveAndFlush(movieEntity);
                            return modelMapper.map(movieEntity, MovieDetail.class);
                        })
                );
    }

    @Override
    public boolean delete(int id) {
        return movieRepository.findById(id)
                        .map(movieEntity -> {
                            movieRepository.deleteById(id);
                            movieRepository.flush();
                            return true;
                        })
                .orElse(false);
    }
}
