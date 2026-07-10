package com.bookingapp.booking_service.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.booking_service.dto.BookingResponse;
import com.bookingapp.booking_service.dto.CreateBookingRequest;
import com.bookingapp.booking_service.service.BookingService;

import jakarta.validation.Valid;

@RestController

@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api/bookings")

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;

    }

    @PostMapping

    public BookingResponse createBooking(@Valid @RequestBody CreateBookingRequest request) {

        return bookingService.createBooking(request);

    }

}