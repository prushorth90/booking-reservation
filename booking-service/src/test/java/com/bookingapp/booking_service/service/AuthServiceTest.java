package com.bookingapp.booking_service.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import com.bookingapp.booking_service.dto.AuthResponse;
import com.bookingapp.booking_service.dto.LoginRequest;
import com.bookingapp.booking_service.dto.RegisterRequest;
import com.bookingapp.booking_service.model.AppUser;
import com.bookingapp.booking_service.model.UserRole;
import com.bookingapp.booking_service.repository.AppUserRepository;

@ExtendWith(MockitoExtension.class)

class AuthServiceTest {

    @Mock

    private AppUserRepository appUserRepository;

    @Mock

    private PasswordEncoder passwordEncoder;

    @Mock

    private JwtService jwtService;

    @InjectMocks

    private AuthService authService;

    @Test

    void register_createsCustomerAndReturnsToken() {

        RegisterRequest request = new RegisterRequest();

        ReflectionTestUtils.setField(request, "name", "Test Customer");

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "password123");

        when(appUserRepository.existsByEmailIgnoreCase("customer@example.com"))

                .thenReturn(false);

        when(passwordEncoder.encode("password123"))

                .thenReturn("hashed-password");

        when(appUserRepository.save(any(AppUser.class)))

                .thenAnswer(invocation -> invocation.getArgument(0));

        when(jwtService.generateToken(any(AppUser.class)))

                .thenReturn("fake-jwt-token");

        AuthResponse response = authService.register(request);

        assertEquals("fake-jwt-token", response.getToken());

        assertEquals("Test Customer", response.getName());

        assertEquals("customer@example.com", response.getEmail());

        assertEquals("CUSTOMER", response.getRole());

        verify(appUserRepository).save(any(AppUser.class));

    }

    @Test

    void register_throwsException_whenEmailAlreadyExists() {

        RegisterRequest request = new RegisterRequest();

        ReflectionTestUtils.setField(request, "name", "Test Customer");

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "password123");

        when(appUserRepository.existsByEmailIgnoreCase("customer@example.com"))

                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(

                IllegalArgumentException.class,

                () -> authService.register(request)

        );

        assertEquals("Email is already registered", exception.getMessage());

        verify(appUserRepository, never()).save(any(AppUser.class));

    }

    @Test

    void login_returnsToken_whenPasswordMatches() {

        LoginRequest request = new LoginRequest();

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "password123");

        AppUser user = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        when(appUserRepository.findByEmailIgnoreCase("customer@example.com"))

                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("password123", "hashed-password"))

                .thenReturn(true);

        when(jwtService.generateToken(user))

                .thenReturn("fake-jwt-token");

        AuthResponse response = authService.login(request);

        assertEquals("fake-jwt-token", response.getToken());

        assertEquals("Test Customer", response.getName());

        assertEquals("customer@example.com", response.getEmail());

        assertEquals("CUSTOMER", response.getRole());

    }

    @Test

    void login_throwsException_whenPasswordDoesNotMatch() {

        LoginRequest request = new LoginRequest();

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "wrong-password");

        AppUser user = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        when(appUserRepository.findByEmailIgnoreCase("customer@example.com"))

                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("wrong-password", "hashed-password"))

                .thenReturn(false);

        IllegalArgumentException exception = assertThrows(

                IllegalArgumentException.class,

                () -> authService.login(request)

        );

        assertEquals("Invalid email or password", exception.getMessage());

    }

}