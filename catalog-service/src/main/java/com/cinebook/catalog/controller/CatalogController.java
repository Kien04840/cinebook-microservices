package com.cinebook.catalog.controller;

import com.cinebook.catalog.dto.MovieShowsResponseDTO;
import com.cinebook.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/movies")
    public String getAllMovies() {
        // Trong thực tế, bạn sẽ gọi service để truy vấn DB
        return "Returning a list of all movies from catalog-service";
    }

    @GetMapping("/movies/{movieId}")
    public String getMovieById(@PathVariable String movieId) {
        return "Returning details for movie ID: " + movieId + " from catalog-service";
    }

    @GetMapping("/shows/search")
    public ResponseEntity<MovieShowsResponseDTO> searchShows(
            @RequestParam String movieId,
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        MovieShowsResponseDTO response = catalogService.findShowsByMovieAndLocation(movieId, city, date);
        return ResponseEntity.ok(response);
    }
}
