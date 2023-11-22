package org.example.movieapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class MovieCreate {
    @NotBlank
    @Size(max=300)
    private String title;

    @Min(1888)
    @NotNull
    private Integer year;

    @Min(60)
    private Integer duration;

    @Size(max=4000)
    private String synopsis;

    @Size(max=300)
    private String posterUri;
}
