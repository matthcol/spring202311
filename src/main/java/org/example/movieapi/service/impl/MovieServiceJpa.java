package org.example.movieapi.service.impl;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceJpa implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;
    // https://modelmapper.org/user-manual/

    @Override
    public List<MovieSimple> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movieEntity -> modelMapper.map(movieEntity, MovieSimple.class))
                .toList();
    }

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
    } // valid transaction here if no exception
}
