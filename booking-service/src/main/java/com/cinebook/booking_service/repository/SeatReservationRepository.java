package com.cinebook.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinebook.booking_service.entity.SeatReservation;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, String> {

}
