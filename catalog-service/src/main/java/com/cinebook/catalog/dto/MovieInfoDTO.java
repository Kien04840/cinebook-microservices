package com.cinebook.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieInfoDTO {
    private String id;
    private String title;
    private String posterUrl;
    private int durationMinutes;
}
