package com.directa24.main.challenge.controller;

import com.directa24.main.challenge.dto.DirectorsResponse;
import com.directa24.main.challenge.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Movie Directors controller", description = "Controller with methods to get directors that have directed a certain amount of movies")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
})
@RequestMapping(value = "${api.context-path}", produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesController {

    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Operation(summary = "Gets movie directors that have directed an amount of movies equal or superior to threshold")
    @GetMapping("/api/directors")
    public ResponseEntity<DirectorsResponse> getDirectorsForThreshold(@RequestParam(value = "threshold") int threshold) throws Exception {
        return ResponseEntity.ok(new DirectorsResponse(
                moviesService.getDirectorsFromThreshold(threshold)
        ));

    }
}
