package com.bookingapp.booking_service.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HotelSearchRequest {

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Check in date is required")
    @FutureOrPresent(message = "Check in date cannot be in the past")
    private LocalDate checkIn;

    @NotNull(message = "Check out date is required")
    @FutureOrPresent(message = "Check out date cannot be in the past")
    private LocalDate checkOut;

    @Min(value = 1, message = "Guests must be at least 1")

    private int guests = 1;

    public HotelSearchRequest(String location, LocalDate checkIn, LocalDate checkOut, int guests) {

        this.location = location;

        this.checkIn = checkIn;

        this.checkOut = checkOut;

        this.guests = guests;

    }

    public String getLocation() {

        return location;

    }

    public LocalDate getCheckIn() {

        return checkIn;

    }

    public LocalDate getCheckOut() {

        return checkOut;

    }

    public int getGuests() {

        return guests;

    }

}