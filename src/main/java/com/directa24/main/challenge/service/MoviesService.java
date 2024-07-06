package com.directa24.main.challenge.service;

import com.directa24.main.challenge.client.MoviesClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        moviesClient.consumeMoviesApi().forEach(
                movieDTO -> directors.add(movieDTO.director()));
        return directors.stream().filter(
                director -> Collections.frequency(directors, director) > threshold)
                .distinct().sorted().toList();
    }
}
