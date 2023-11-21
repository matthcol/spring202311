package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class PersonCreate {
    private String name;
    private LocalDate birthdate;

}
