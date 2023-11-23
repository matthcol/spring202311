package org.example.movieapi.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class DataExceptionHandler {

    // Solution 1

//    @ResponseStatus(
//            value= HttpStatus.CONFLICT,
//            reason = "Data Integrity Violation"
//    )
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void handleDataIntegrityViolationException(){
//
//    }

    // Solution 2

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(){
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                "Data Integrity Violation"
        );
    }


}
