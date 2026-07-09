package com.bookingapp.booking_service.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.booking_service.dto.HotelSearchResponse;
import com.bookingapp.booking_service.service.HotelService;

@RestController

@CrossOrigin(origins = "http://localhost:5173")

public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {

        this.hotelService = hotelService;

    }

    @GetMapping("/api/hotels/search")

    public List<HotelSearchResponse> searchHotels(

            @RequestParam String location,

            @RequestParam LocalDate checkIn,

            @RequestParam LocalDate checkOut,

            @RequestParam(defaultValue = "1") int guests

    ) {

        return hotelService.searchHotels(location, checkIn, checkOut, guests);

    }

}