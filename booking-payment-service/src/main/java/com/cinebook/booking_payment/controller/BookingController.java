package com.cinebook.booking_payment.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @PostMapping
    public String createBooking() {
        // Logic để tạo một booking mới
        return "Booking created successfully!";
    }

    @GetMapping("/{bookingId}")
    public String getBookingDetails(@PathVariable String bookingId) {
        return "Details for booking ID: " + bookingId;
    }
}
