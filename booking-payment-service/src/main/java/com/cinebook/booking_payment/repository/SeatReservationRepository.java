package com.cinebook.booking_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinebook.booking_payment.entity.SeatReservation;

public interface SeatReservationRepository extends JpaRepository<SeatReservation, String> {

}
