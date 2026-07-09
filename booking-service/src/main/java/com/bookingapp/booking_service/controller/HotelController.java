package com.bookingapp.booking_service.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.booking_service.dto.HotelSearchResponse;
import com.bookingapp.booking_service.service.HotelService;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@RestController

@Validated

@CrossOrigin(origins = "http://localhost:5173")

public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {

        this.hotelService = hotelService;

    }

    @GetMapping("/api/hotels/search")

    public List<HotelSearchResponse> searchHotels(

            @RequestParam @NotBlank(message = "Location is required") String location,

            @RequestParam @FutureOrPresent(message = "Check in date cannot be in the past") LocalDate checkIn,

            @RequestParam @FutureOrPresent(message = "Check out date cannot be in the past") LocalDate checkOut,

            @RequestParam(defaultValue = "1") @Min(value = 1, message = "Guests must be at least 1") int guests

    ) {

        return hotelService.searchHotels(location, checkIn, checkOut, guests);

    }

}