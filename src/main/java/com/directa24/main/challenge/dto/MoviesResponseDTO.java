package com.directa24.main.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Objects;

public record MoviesResponseDTO(

        @NotNull
        @JsonProperty("page")
        int page,

        @NotNull
        @JsonProperty("per_page")
        int perPage,

        @NotNull
        @JsonProperty("total")
        int total,

        @NotNull
        @JsonProperty("total_pages")
        int totalPages,

        @NotNull
        @JsonProperty("data")
        @Valid MovieDTO[] data
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesResponseDTO response = (MoviesResponseDTO) o;
        return page == response.page && total == response.total && perPage == response.perPage && totalPages == response.totalPages && Objects.deepEquals(data, response.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, perPage, total, totalPages, Arrays.hashCode(data));
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", total=" + total +
                ", totalPages=" + totalPages +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}