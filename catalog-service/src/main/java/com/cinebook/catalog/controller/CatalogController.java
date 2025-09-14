package com.cinebook.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @GetMapping("/movies")
    public String getAllMovies() {
        // Trong thực tế, bạn sẽ gọi service để truy vấn DB
        return "Returning a list of all movies from catalog-service";
    }

    @GetMapping("/movies/{movieId}")
    public String getMovieById(@PathVariable String movieId) {
        return "Returning details for movie ID: " + movieId + " from catalog-service";
    }
}
