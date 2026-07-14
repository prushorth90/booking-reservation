package com.bookingapp.booking_service.controller;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookingapp.booking_service.model.AppUser;
import com.bookingapp.booking_service.model.UserRole;
import com.bookingapp.booking_service.repository.AppUserRepository;
import com.bookingapp.booking_service.service.JwtService;

@SpringBootTest

@AutoConfigureMockMvc

class SecurityIntegrationTest {

    @Autowired

    private MockMvc mockMvc;

    @Autowired

    private JwtService jwtService;

    @MockitoBean

    private AppUserRepository appUserRepository;

    @Test

    void bookingsEndpoint_rejectsUnauthenticatedUser() throws Exception {

        mockMvc.perform(get("/api/bookings"))

                .andExpect(status().isForbidden());

    }

    @Test

    void bookingsEndpoint_allowsCustomerToken() throws Exception {

        AppUser customer = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        when(appUserRepository.findByEmailIgnoreCase("customer@example.com"))

                .thenReturn(Optional.of(customer));

        String token = jwtService.generateToken(customer);

        mockMvc.perform(get("/api/bookings")

                        .header("Authorization", "Bearer " + token))

                .andExpect(status().isOk());

    }

    @Test

    void adminBookingsEndpoint_rejectsCustomerToken() throws Exception {

        AppUser customer = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        String token = jwtService.generateToken(customer);

        mockMvc.perform(get("/api/admin/bookings")

                        .header("Authorization", "Bearer " + token))

                .andExpect(status().isForbidden());

    }

    @Test

    void adminBookingsEndpoint_allowsAdminToken() throws Exception {

        AppUser admin = new AppUser(

                "Admin User",

                "admin@example.com",

                "hashed-password",

                UserRole.ADMIN

        );

        String token = jwtService.generateToken(admin);

        mockMvc.perform(get("/api/admin/bookings")

                        .header("Authorization", "Bearer " + token))

                .andExpect(status().isOk());

    }

}