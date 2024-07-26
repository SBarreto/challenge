package com.directa24.main.challenge.service;

import com.directa24.main.challenge.client.MoviesClient;
import com.directa24.main.challenge.dto.MovieDTO;
import com.directa24.main.challenge.dto.MoviesResponseDTO;
import com.directa24.main.challenge.enums.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {

    @Mock
    private MoviesClient moviesClient;

    @InjectMocks
    private MoviesService moviesService;

    @Test
    void testGetDirectorsFromThreshold() throws IOException {

        List<MovieDTO> movieList = new ArrayList<>();
        movieList.add(new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        ));
        movieList.add(new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        ));
        MoviesResponseDTO moviesResponse = new MoviesResponseDTO(
                1,
                2,
                4,
                1,
                movieList.toArray(new MovieDTO[0])
        );

        when(moviesClient.callMoviesApi(anyInt())).thenReturn(moviesResponse);

        List<String> directors = moviesService.getDirectorsFromThreshold(1);

        assertThat(directors, notNullValue());
        assertThat(directors.get(0), equalTo("John Doe"));
    }
}
