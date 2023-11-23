package org.example.movieapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// NB: alt handle this exception with controller advice
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "data not found")
public class NotFoundException extends RuntimeException {

    private String name;
    private int id;

    public NotFoundException(String message, String name, int id) {
        super(message);
        this.name = name;
        this.id = id;
    }
}
