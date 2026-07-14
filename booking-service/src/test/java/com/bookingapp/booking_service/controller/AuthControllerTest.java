package com.bookingapp.booking_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookingapp.booking_service.dto.AuthResponse;
import com.bookingapp.booking_service.dto.LoginRequest;
import com.bookingapp.booking_service.dto.RegisterRequest;
import com.bookingapp.booking_service.exception.GlobalExceptionHandler;
import com.bookingapp.booking_service.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)

class AuthControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock

    private AuthService authService;

    @BeforeEach

    void setUp() {

        AuthController authController = new AuthController(authService);

        mockMvc = MockMvcBuilders

                .standaloneSetup(authController)

                .setControllerAdvice(new GlobalExceptionHandler())

                .build();

        objectMapper = new ObjectMapper();

    }

    @Test

    void register_returnsAuthResponse() throws Exception {

        RegisterRequest request = new RegisterRequest();

        ReflectionTestUtils.setField(request, "name", "Test Customer");

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "password123");

        AuthResponse response = new AuthResponse(

                "fake-jwt-token",

                1L,

                "Test Customer",

                "customer@example.com",

                "CUSTOMER"

        );

        when(authService.register(any(RegisterRequest.class)))

                .thenReturn(response);

        mockMvc.perform(post("/api/auth/register")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.token").value("fake-jwt-token"))

                .andExpect(jsonPath("$.userId").value(1))

                .andExpect(jsonPath("$.name").value("Test Customer"))

                .andExpect(jsonPath("$.email").value("customer@example.com"))

                .andExpect(jsonPath("$.role").value("CUSTOMER"));

    }

    @Test

    void login_returnsAuthResponse() throws Exception {

        LoginRequest request = new LoginRequest();

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "password123");

        AuthResponse response = new AuthResponse(

                "fake-jwt-token",

                1L,

                "Test Customer",

                "customer@example.com",

                "CUSTOMER"

        );

        when(authService.login(any(LoginRequest.class)))

                .thenReturn(response);

        mockMvc.perform(post("/api/auth/login")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.token").value("fake-jwt-token"))

                .andExpect(jsonPath("$.email").value("customer@example.com"))

                .andExpect(jsonPath("$.role").value("CUSTOMER"));

    }

    @Test

    void login_returnsBadRequest_whenServiceThrowsException() throws Exception {

        LoginRequest request = new LoginRequest();

        ReflectionTestUtils.setField(request, "email", "customer@example.com");

        ReflectionTestUtils.setField(request, "password", "wrong-password");

        when(authService.login(any(LoginRequest.class)))

                .thenThrow(new IllegalArgumentException("Invalid email or password"));

        mockMvc.perform(post("/api/auth/login")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isBadRequest())

                .andExpect(content().string("Invalid email or password"));

    }

}