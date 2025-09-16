package com.cinebook.catalog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ShowInfoDTO {
    private String showId;
    private String hallName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String language;
    private String subtitle;
    private BigDecimal basePrice;
}
