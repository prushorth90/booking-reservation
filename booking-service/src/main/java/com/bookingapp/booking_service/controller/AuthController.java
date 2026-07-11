package com.bookingapp.booking_service.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.booking_service.dto.AuthResponse;
import com.bookingapp.booking_service.dto.LoginRequest;
import com.bookingapp.booking_service.dto.RegisterRequest;
import com.bookingapp.booking_service.service.AuthService;

import jakarta.validation.Valid;

@RestController

@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;

    }

    @PostMapping("/register")

    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {

        return authService.register(request);

    }

    @PostMapping("/login")

    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);

    }

}
