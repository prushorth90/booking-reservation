package com.bookingapp.booking_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.booking_service.dto.CreateHotelRequest;
import com.bookingapp.booking_service.dto.CreateRoomRequest;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.service.AdminService;

import jakarta.validation.Valid;

@RestController

@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api/admin")

public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {

        this.adminService = adminService;

    }

    @GetMapping("/hotels")

    public List<Hotel> getHotels() {

        return adminService.getHotels();

    }

    @PostMapping("/hotels")

    public Hotel createHotel(@Valid @RequestBody CreateHotelRequest request) {

        return adminService.createHotel(request);

    }

    @PostMapping("/hotels/{hotelId}/rooms")

    public Room createRoom(

            @PathVariable Long hotelId,

            @Valid @RequestBody CreateRoomRequest request

    ) {

        return adminService.createRoom(hotelId, request);

    }

}
