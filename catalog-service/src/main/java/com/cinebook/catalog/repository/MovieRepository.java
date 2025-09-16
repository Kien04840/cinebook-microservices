package com.cinebook.catalog.repository;

import com.cinebook.catalog.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByMovieId(String movieId);

    List<Movie> findByGenresIn(List<String> genres);
}
