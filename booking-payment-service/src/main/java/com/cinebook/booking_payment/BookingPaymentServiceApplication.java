package com.cinebook.booking_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BookingPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingPaymentServiceApplication.class, args);
	}

}
