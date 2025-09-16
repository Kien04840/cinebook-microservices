package com.cinebook.catalog.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Document(collection = "movies")
@Getter
@Setter
public class Movie {

    @Id
    private String id;

    @Field("movie_id")
    @Indexed(unique = true)
    private String movieId;

    @Indexed(unique = true)
    private String title;

    private String description;

    @Field("release_date")
    private Date releaseDate;

    private String director;

    private List<CastMember> cast;

    private List<String> genres;

    @Field("duration_minutes")
    private int durationMinutes;

    @Field("poster_url")
    private String posterUrl;

    @Field("trailer_url")
    private String trailerUrl;

    private String rating; // G, PG, PG-13, R, NC-17

    private String language;

    private List<String> subtitles;

    private String production;

    private List<String> tags;

    @CreatedDate
    @Field("created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private Instant updatedAt;
}
