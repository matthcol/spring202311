package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class MovieCreate {
    private String title;
    private int year;
    private Integer duration;
    private String synopsis;
    private String posterUri;
}
