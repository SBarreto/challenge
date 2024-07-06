package com.directa24.main.challenge.client;

import com.directa24.main.challenge.dto.MovieDTO;
import com.directa24.main.challenge.dto.MoviesResponse;
import com.directa24.main.challenge.exception.MovieClientApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MoviesClient {

    @Value("${MOVIES_API_URL}")
    private String moviesApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<MovieDTO> consumeMoviesApi() {

        String uri = UriComponentsBuilder.fromUriString(moviesApiUrl)
                .queryParam("page", 1)
                .toUriString();

        int pageTotal = 0;
        List<MovieDTO> moviesToProcess = new ArrayList<>();
        try {
            ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);
            pageTotal = objectMapper.readValue(response.getBody(), MoviesResponse.class).totalPages();
            for (int i = 1; i <= pageTotal; i++) {
                uri = UriComponentsBuilder.fromUriString(moviesApiUrl)
                        .queryParam("page", i)
                        .toUriString();
                response = restTemplate.getForEntity(uri, byte[].class);
                MovieDTO[] movies = objectMapper.readValue(response.getBody(), MoviesResponse.class).data();
                moviesToProcess.addAll(Arrays.stream(movies).toList());
            }
        } catch (IOException e) {
            throw new MovieClientApiException("There was an error trying to fetch movies from api");
        }
        return moviesToProcess;
    }
}
