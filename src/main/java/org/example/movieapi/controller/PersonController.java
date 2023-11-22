package org.example.movieapi.controller;

import jakarta.validation.Valid;
import org.example.movieapi.dto.PersonCreate;
import org.example.movieapi.dto.PersonSimple;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @PostMapping
    public PersonSimple add(@Valid @RequestBody PersonCreate person) {
        return null;
    }
}
