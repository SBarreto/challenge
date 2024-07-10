package com.directa24.main.challenge.directors;

import com.directa24.main.challenge.client.MoviesClient;
import com.directa24.main.challenge.dto.MovieDTO;
import com.directa24.main.challenge.service.MoviesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {

    @Mock
    private MoviesClient moviesClient;

    @InjectMocks
    private MoviesService moviesService;

    @Test
    void testGetDirectorsFromThreshold() {

        List<MovieDTO> movieList = new ArrayList<>();
        movieList.add(new MovieDTO(
                "title",
                2014,
                "R",
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        ));

        when(moviesClient.consumeMoviesApi()).thenReturn(movieList);

        List<String> directors = moviesService.getDirectorsFromThreshold(1);

        assertThat(directors, notNullValue());
        assertThat(directors.get(0), equalTo("John Doe"));
    }
}
