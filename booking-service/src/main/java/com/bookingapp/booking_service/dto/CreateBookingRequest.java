package com.bookingapp.booking_service.dto;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {

    @NotNull(message = "Room ID is required")

    private Long roomId;

    @NotBlank(message = "Guest name is required")

    private String guestName;

    @NotNull(message = "Check in date is required")

    @FutureOrPresent(message = "Check in date cannot be in the past")

    private LocalDate checkInDate;

    @NotNull(message = "Check out date is required")

    @FutureOrPresent(message = "Check out date cannot be in the past")

    private LocalDate checkOutDate;

    public Long getRoomId() {

        return roomId;

    }

    public String getGuestName() {

        return guestName;

    }

    public LocalDate getCheckInDate() {

        return checkInDate;

    }

    public LocalDate getCheckOutDate() {

        return checkOutDate;

    }

}
