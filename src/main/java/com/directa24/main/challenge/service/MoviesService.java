package com.directa24.main.challenge.service;

import com.directa24.main.challenge.client.MoviesClient;
import com.directa24.main.challenge.dto.MovieDTO;
import com.directa24.main.challenge.dto.MoviesResponseDTO;
import com.directa24.main.challenge.exception.MovieClientApiException;
import com.directa24.main.challenge.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MoviesService {

    private final MoviesClient moviesClient;

    public MoviesService(MoviesClient moviesClient) {
        this.moviesClient = moviesClient;
    }

    public List<String> getDirectorsFromThreshold(int threshold) {
        List<String> directors = new ArrayList<>();
        getAndValidateMovies().forEach(
                movieDTO -> directors.add(movieDTO.director()));
        return directors.stream().filter(
                director -> Collections.frequency(directors, director) > threshold)
                .distinct().sorted().toList();
    }

    private List<MovieDTO> getAndValidateMovies() {
        List<MoviesResponseDTO> moviesResponses = consumeMoviesApi();
        ValidationUtils.validateResponseFields(moviesResponses);
        List<MovieDTO> moviesList = new ArrayList<>();
        moviesResponses.forEach(
                moviesResponse -> moviesList.addAll(Arrays.stream(moviesResponse.data()).toList())
        );
        return moviesList;
    }

    private List<MoviesResponseDTO> consumeMoviesApi() {
        int pageTotal;
        List<MoviesResponseDTO> moviesResponses = new ArrayList<>();
        try {
            pageTotal =  moviesClient.callMoviesApi(1).totalPages();
            for (int i = 1; i <= pageTotal; i++) {
                moviesResponses.add(moviesClient.callMoviesApi(i));
            }
        } catch (Exception e) {
            throw new MovieClientApiException("There was an error trying to fetch from movies service: " + e.getMessage());
        }
        return moviesResponses;

    }
}
