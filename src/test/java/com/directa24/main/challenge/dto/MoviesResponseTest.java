package com.directa24.main.challenge.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoviesResponseTest {

    private Validator validator;

    @BeforeEach
    void beforeEach() {
        ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
        validator = validationFactory.getValidator();
    }

    @Test
    void whenMovieArrayIsNull_addToViolations() {
        MoviesResponseDTO badResponse = new MoviesResponseDTO(
                1,
                1,
                1,
                1,
                null
        );

        Set<ConstraintViolation<MoviesResponseDTO>> violations = validator.validate(badResponse);
        assertEquals(1, violations.size());
    }
}
