package com.directa24.main.challenge.dto;

import com.directa24.main.challenge.enums.Rating;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MovieDTO(

        @NotNull
        @JsonProperty("Title")
        String title,

        @NotNull
        @Min(value = 1888, message = "Release year can't be before 1888, unless it's Back to the Future")
        @Max(value = 2024, message = "Release year can't be after 2024, unless it's Back to the Future")
        @JsonProperty("Year")
        int year,

        @NotNull
        @JsonProperty("Rated")
        Rating rated,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
        @JsonProperty("Released")
        Date released,

        @NotNull
        @JsonProperty("Runtime")
        String runtime,

        @NotNull
        @JsonProperty("Genre")
        String genre,

        @NotBlank(message = "Director name must not be blank")
        @JsonProperty("Director")
        String director,

        @NotNull
        @JsonProperty("Writer")
        String writer,

        @NotNull
        @JsonProperty("Actors")
        String actors
) {
}
