package com.bookingapp.booking_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.booking_service.dto.HotelSearchResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HotelController {

    @GetMapping("/api/hotels/search")
    public List<HotelSearchResponse> searchHotels(
            @RequestParam String location,
            @RequestParam LocalDate checkIn,
            @RequestParam LocalDate checkOut,
            @RequestParam(defaultValue = "1") int guests
    ) {
        return List.of(
                new HotelSearchResponse(
                        1L,
                        "Downtown Seattle Hotel",
                        "Seattle",
                        "123 Pike Street",
                        4.5,
                        180,
                        4,
                        "https://images.unsplash.com/photo-1566073771259-6a8506099945"
                ),
                new HotelSearchResponse(
                        2L,
                        "Lake View Suites",
                        "Seattle",
                        "88 Lake Union Ave",
                        4.7,
                        230,
                        2,
                        "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa"
                ),
                new HotelSearchResponse(
                        3L,
                        "Airport Business Inn",
                        "Seattle",
                        "500 Airport Way",
                        4.1,
                        140,
                        6,
                        "https://images.unsplash.com/photo-1564501049412-61c2a3083791"
                )
        );
    }
}