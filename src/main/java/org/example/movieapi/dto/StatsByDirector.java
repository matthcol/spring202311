package org.example.movieapi.dto;

import java.time.LocalDate;

public interface StatsByDirector {
    Integer getDirectorId();
    String getDirectorName();
    LocalDate getDirectorBirthdate();
    long getMovieCount();
    int getTotalDuration();
    Integer getFirstYear();
    Integer getLastYear();
}
