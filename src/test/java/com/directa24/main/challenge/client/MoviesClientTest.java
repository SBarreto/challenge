package com.directa24.main.challenge.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@EnableRetry
@SpringBootTest
class MoviesClientTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MoviesClient moviesClient;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(moviesClient, "moviesServiceBaseUrl", "https://eroninternational-movies.wiremockapi.cloud");
    }

    @Test
    void testMovieClientRetries() {

        when(restTemplate.getForEntity(anyString(), eq(byte[].class)))
                .thenThrow(new RuntimeException("error"));

        try {
            moviesClient.callMoviesApi(1);
        } catch (Exception e) {
            //expected error
        }

        verify(restTemplate, times(5)).getForEntity(anyString(), eq(byte[].class));
    }
}
