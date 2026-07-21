package com.bookingapp.booking_service.controller;
 
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookingapp.booking_service.dto.HotelSearchResponse;
import com.bookingapp.booking_service.exception.GlobalExceptionHandler;
import com.bookingapp.booking_service.service.HotelService;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HotelService hotelService;
    
    @BeforeEach
    void setUp() {
        HotelController hotelController = new HotelController(hotelService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(hotelController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void searchHotels_returnsAvailableHotels() throws Exception {
        when(hotelService.searchHotels(
                "Seattle",
                LocalDate.of(2026, 7, 20),
                LocalDate.of(2026, 7, 23),
                2
        )).thenReturn(List.of(
                new HotelSearchResponse(
                        1L,
                        "Downtown Seattle Hotel",
                        "Seattle",
                        "123 Pike Street",
                        4.5,
                        180,
                        2,
                        "image-url",
                        10L,
                        "Queen"
                )
        ));

        mockMvc.perform(get("/api/hotels/search")
                        .param("location", "Seattle")
                        .param("checkIn", "2026-07-20")
                        .param("checkOut", "2026-07-23")
                        .param("guests", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Downtown Seattle Hotel"))
                .andExpect(jsonPath("$[0].city").value("Seattle"))
                .andExpect(jsonPath("$[0].pricePerNight").value(180))
                .andExpect(jsonPath("$[0].availableRooms").value(2))
                .andExpect(jsonPath("$[0].roomId").value(10))
                .andExpect(jsonPath("$[0].roomType").value("Queen"));
    }

    @Test
    void searchHotels_returnsBadRequest_whenCheckoutBeforeCheckin() throws Exception {
        when(hotelService.searchHotels(
                "Seattle",
                LocalDate.of(2026, 7, 23),
                LocalDate.of(2026, 7, 20),
                2
        )).thenThrow(new IllegalArgumentException("Check out date must be after check in date"));

        mockMvc.perform(get("/api/hotels/search")
                        .param("location", "Seattle")
                        .param("checkIn", "2026-07-23")
                        .param("checkOut", "2026-07-20")
                        .param("guests", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Check out date must be after check in date"));
    }
}