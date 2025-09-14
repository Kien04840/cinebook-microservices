package com.cinebook.booking_payment.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/{bookingId}/payment")
public class PaymentController {

    @PostMapping
    public String processPayment(@PathVariable String bookingId) {
        // Logic xử lý thanh toán cho bookingId
        return "Payment processed for booking ID: " + bookingId;
    }

    @GetMapping
    public String getPaymentStatus(@PathVariable String bookingId) {
        return "Payment status for booking ID: " + bookingId + " is COMPLETED";
    }
}
