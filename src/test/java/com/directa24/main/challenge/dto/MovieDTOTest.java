package com.directa24.main.challenge.dto;

import com.directa24.main.challenge.enums.Rating;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieDTOTest {

    private Validator validator;

    @BeforeEach
    void beforeEach() {
        ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
        validator = validationFactory.getValidator();
    }

    @Test
    void whenTitleIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                null,
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }

    @Test
    void whenRatingIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                null,
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }

    @Test
    void whenReleaseDateIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                null,
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }


    @Test
    void whenRuntimeIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                null,
                "Action",
                "John Doe",
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }

    @Test
    void whenGenreIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                null,
                "John Doe",
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }

    @Test
    void whenDirectorIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                null,
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }

    @Test
    void whenDirectorNameIsBlank_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                " ",
                "Jane Doe",
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
        assertEquals("Director name must not be blank", violations.iterator().next().getMessage());
    }

    @Test
    void whenWriterIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                null,
                "actor list"
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }

    @Test
    void whenActorsIsNull_addToViolations() {
        MovieDTO badMovie = new MovieDTO(
                "title",
                2014,
                Rating.R,
                new Date(),
                "2:30",
                "Action",
                "John Doe",
                "Jane Doe",
                null
        );
        Set<ConstraintViolation<MovieDTO>> violations = validator.validate(badMovie);
        assertEquals(1, violations.size());
    }


}
