package com.directa24.main.challenge.utils;

import com.directa24.main.challenge.dto.MoviesResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {

    private ValidationUtils() {}

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static void validateResponseFields(List<MoviesResponseDTO> moviesResponses) {
        Set<ConstraintViolation<List<MoviesResponseDTO>>> violations = validator.validate(moviesResponses);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(
                            violation -> String.format("Property '%s': %s (Invalid value: %s)",
                                    violation.getPropertyPath(),
                                    violation.getMessage(),
                                    violation.getInvalidValue()))
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
