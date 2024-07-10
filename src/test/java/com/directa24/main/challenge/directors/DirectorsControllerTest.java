package com.directa24.main.challenge.directors;

import com.directa24.main.challenge.controller.DirectorsController;
import com.directa24.main.challenge.dto.DirectorsResponse;
import com.directa24.main.challenge.service.MoviesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DirectorsControllerTest {

    @Mock
    private MoviesService moviesService;

    @InjectMocks
    private DirectorsController directorsController;


    @Test
    void testGetDirectorsWithThreshold() {
        List<String> mockDirectors = new ArrayList<>();
        mockDirectors.add("John Doe");

        when(moviesService.getDirectorsFromThreshold(4)).thenReturn(mockDirectors);

        ResponseEntity<DirectorsResponse> responseResponseEntity = directorsController.getDirectorsForThreshold(4);

        assertThat(responseResponseEntity, notNullValue());
        assertThat(responseResponseEntity.getStatusCode(), equalTo(HttpStatus.OK));

    }


}
