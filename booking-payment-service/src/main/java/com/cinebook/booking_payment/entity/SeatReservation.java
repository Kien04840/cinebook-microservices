package com.cinebook.booking_payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "seat_reservations", uniqueConstraints = {
    @UniqueConstraint(name = "uq_show_seat", columnNames = {"show_id", "seat_label"})
})
@Getter
@Setter
public class SeatReservation {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "booking_id", length = 36)
    private String bookingId;

    @Column(name = "show_id", nullable = false, length = 36)
    private String showId;

    @Column(name = "seat_label", nullable = false, length = 10)
    private String seatLabel;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;
}
