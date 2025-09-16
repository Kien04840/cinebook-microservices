package com.cinebook.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ShowsByCinemaDTO {
    private String cinemaId;
    private String cinemaName;
    private String cinemaLocation;
    private List<ShowInfoDTO> shows;
}
