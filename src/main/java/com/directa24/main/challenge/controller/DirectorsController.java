package com.directa24.main.challenge.controller;

import com.directa24.main.challenge.dto.DirectorsResponseDTO;
import com.directa24.main.challenge.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Movie Directors controller", description = "Controller with methods to get directors that have directed a certain amount of movies")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success retrieving movies and directors for threshold"),
        @ApiResponse(responseCode = "400", description = "Bad request, tried to send invalidad threshold parameter", content = @Content),
        @ApiResponse(responseCode = "404", description = "Path Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error, there was an issue contacting external movies API or processing data", content = @Content)
})
@RequestMapping(value = "${api.context-path}", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class DirectorsController {

    private final MoviesService moviesService;

    public DirectorsController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Operation(summary = "Gets movie directors that have directed an amount of movies equal or superior to threshold")
    @GetMapping("/api/directors")
    @Valid
    @Parameter(name = "threshold", description = "Exclusive lower limit for number of movies made by any director, must be a number from 1 to 100", required = true, schema = @Schema(type = "integer", format = "int32", example = "5"))
    public ResponseEntity<DirectorsResponseDTO> getDirectorsForThreshold(@RequestParam @Pattern(regexp = "^[1-9][0-9]?$|^100$", message = "Only numbers from 1 to 100 are accepted") String threshold) {
        int thresholdInt = Integer.parseInt(threshold);
        return ResponseEntity.ok(new DirectorsResponseDTO(
                thresholdInt,
                moviesService.getDirectorsFromThreshold(thresholdInt)
        ));

    }
}
