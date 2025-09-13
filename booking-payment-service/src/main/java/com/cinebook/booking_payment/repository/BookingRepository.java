package com.cinebook.booking_payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinebook.booking_payment.entity.Booking;


@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
   
}
