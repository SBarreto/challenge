package com.directa24.main.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public record MovieDTO(
        @JsonProperty("Title")
        String title,
        @JsonProperty("Year")
        int year,
        @JsonProperty("Rated")
        String rated,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy")
        @JsonProperty("Released")
        Date released,
        @JsonProperty("Runtime")
        String runtime,
        @JsonProperty("Genre")
        String genre,
        @JsonProperty("Director")
        String director,
        @JsonProperty("Writer")
        String writer,
        @JsonProperty("Actors")
        String actors
) {
}
