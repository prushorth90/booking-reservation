package com.bookingapp.booking_service.controller;


import com.bookingapp.booking_service.dto.BookingResponse;

import com.bookingapp.booking_service.dto.CreateBookingRequest;

import com.bookingapp.booking_service.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping

    public List<BookingResponse> getBookingsForCurrentUser() {

        return bookingService.getBookingsForCurrentUser();

    }

    @PatchMapping("/{bookingId}/cancel")

    public BookingResponse cancelBooking(@PathVariable Long bookingId) {

        return bookingService.cancelBooking(bookingId);

    }

}