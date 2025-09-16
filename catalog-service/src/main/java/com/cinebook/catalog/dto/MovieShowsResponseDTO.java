package com.cinebook.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class MovieShowsResponseDTO {
    private MovieInfoDTO movie;
    private List<ShowsByCinemaDTO> showsByCinema;
}
