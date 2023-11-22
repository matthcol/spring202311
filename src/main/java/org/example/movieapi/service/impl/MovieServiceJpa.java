package org.example.movieapi.service.impl;

import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
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

    @Override
    public List<MovieSimple> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieDetail> findById(int id) {
        return Optional.empty();
    }
}
