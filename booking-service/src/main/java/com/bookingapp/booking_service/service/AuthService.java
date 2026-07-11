package com.bookingapp.booking_service.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.AuthResponse;
import com.bookingapp.booking_service.dto.LoginRequest;
import com.bookingapp.booking_service.dto.RegisterRequest;
import com.bookingapp.booking_service.model.AppUser;
import com.bookingapp.booking_service.model.UserRole;
import com.bookingapp.booking_service.repository.AppUserRepository;

@Service

public class AuthService {

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(

            AppUserRepository appUserRepository,

            PasswordEncoder passwordEncoder

    ) {

        this.appUserRepository = appUserRepository;

        this.passwordEncoder = passwordEncoder;

    }

    public AuthResponse register(RegisterRequest request) {

        if (appUserRepository.existsByEmailIgnoreCase(request.getEmail())) {

            throw new IllegalArgumentException("Email is already registered");

        }

        AppUser user = new AppUser(

                request.getName(),

                request.getEmail(),

                passwordEncoder.encode(request.getPassword()),

                UserRole.CUSTOMER

        );

        AppUser savedUser = appUserRepository.save(user);

        return new AuthResponse(

                savedUser.getId(),

                savedUser.getName(),

                savedUser.getEmail(),

                savedUser.getRole().name()

        );

    }

    public AuthResponse login(LoginRequest request) {

        AppUser user = appUserRepository.findByEmailIgnoreCase(request.getEmail())

                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {

            throw new IllegalArgumentException("Invalid email or password");

        }

        return new AuthResponse(

                user.getId(),

                user.getName(),

                user.getEmail(),

                user.getRole().name()

        );

    }

}
