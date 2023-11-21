package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class MovieDetail extends MovieSimple {
    private PersonSimple director;
    private Set<PersonSimple> actors;
}
