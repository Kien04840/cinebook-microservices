package com.cinebook.catalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Instant;

@Entity
@Table(name = "shows")
@Getter
@Setter
public class Show {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    /**
     * Nhiều suất chiếu (Show) có thể diễn ra trong một phòng chiếu (Hall).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", nullable = false, referencedColumnName = "id")
    private Hall hall;

    @Column(name = "movie_id", nullable = false, columnDefinition = "CHAR(36)")
    private String movieId; // Sẽ liên kết với Movie entity trong tương lai

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(length = 50)
    private String language;

    @Column(length = 50)
    private String subtitle;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;
}
