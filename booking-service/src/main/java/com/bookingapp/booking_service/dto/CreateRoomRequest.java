package com.bookingapp.booking_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateRoomRequest {

    @NotBlank(message = "Room type is required")

    private String roomType;

    @Min(value = 1, message = "Capacity must be at least 1")

    private int capacity;

    @Min(value = 1, message = "Price per night must be at least 1")

    private int pricePerNight;

    public String getRoomType() {

        return roomType;

    }

    public int getCapacity() {

        return capacity;

    }

    public int getPricePerNight() {

        return pricePerNight;

    }

}