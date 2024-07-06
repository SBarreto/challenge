package com.directa24.main.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public record MoviesResponse(
        @JsonProperty("page")
        int page,
        @JsonProperty("per_page")
        int perPage,
        @JsonProperty("total")
        int total,
        @JsonProperty("total_pages")
        int totalPages,
        @JsonProperty("data")
        MovieDTO[] data
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesResponse response = (MoviesResponse) o;
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