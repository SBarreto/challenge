package com.directa24.main.challenge.client;

import com.directa24.main.challenge.dto.MoviesResponseDTO;
import com.directa24.main.challenge.exception.MovieProviderUnavailableException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class MoviesClient {

    @Value("${movies.service.baseurl}")
    private String moviesServiceBaseUrl;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public MoviesClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public MoviesResponseDTO callMoviesApi(int param) throws IOException {
        String uri = UriComponentsBuilder.fromUriString(moviesServiceBaseUrl + "/api/movies/search")
                .queryParam("page", param)
                .toUriString();
        ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);
        return objectMapper.readValue(response.getBody(), MoviesResponseDTO.class);
    }

    @Recover
    public MoviesResponseDTO recover (Throwable t) {
        throw new MovieProviderUnavailableException("Movies api is unresponsive or unavailable");
    }
}
