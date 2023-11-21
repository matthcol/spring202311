package org.example.movieapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieCreate {
    private String title;
    private int year;
    private Integer duration;
    private String synopsis;
    private String posterUri;
}
