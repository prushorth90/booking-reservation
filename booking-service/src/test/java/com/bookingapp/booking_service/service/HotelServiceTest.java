package com.bookingapp.booking_service.service;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookingapp.booking_service.dto.HotelSearchResponse;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.repository.BookingRepository;
import com.bookingapp.booking_service.repository.HotelRepository;
import com.bookingapp.booking_service.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)

class HotelServiceTest {

    @Mock

    private HotelRepository hotelRepository;

    @Mock

    private RoomRepository roomRepository;

    @Mock

    private BookingRepository bookingRepository;

    @InjectMocks

    private HotelService hotelService;
    
    @Mock

private MetricsService metricsService;
    @Test

    void searchHotels_returnsAvailableHotels() {

        Hotel hotel = new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                0,

                "image-url"

        );

        Room queenRoom = new Room("Queen", 2, 180, hotel);

        Room kingRoom = new Room("King", 2, 220, hotel);

        when(hotelRepository.findByCityIgnoreCase("Seattle"))

                .thenReturn(List.of(hotel));

        when(roomRepository.findByHotelIdAndCapacityGreaterThanEqual(hotel.getId(), 2))

                .thenReturn(List.of(queenRoom, kingRoom));

        when(bookingRepository.existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                any(),

                eq("CONFIRMED"),

                eq(LocalDate.of(2026, 7, 23)),

                eq(LocalDate.of(2026, 7, 20))

        )).thenReturn(false);

        List<HotelSearchResponse> results = hotelService.searchHotels(

                "Seattle",

                LocalDate.of(2026, 7, 20),

                LocalDate.of(2026, 7, 23),

                2

        );

        assertEquals(1, results.size());

        assertEquals("Downtown Seattle Hotel", results.get(0).getName());

        assertEquals(180, results.get(0).getPricePerNight());

        assertEquals(2, results.get(0).getAvailableRooms());

    }

    @Test

    void searchHotels_throwsException_whenCheckoutIsBeforeCheckin() {

        IllegalArgumentException exception = assertThrows(

                IllegalArgumentException.class,

                () -> hotelService.searchHotels(

                        "Seattle",

                        LocalDate.of(2026, 7, 23),

                        LocalDate.of(2026, 7, 20),

                        2

                )

        );

        assertEquals("Check out date must be after check in date", exception.getMessage());

    }

    @Test

    void searchHotels_filtersOutFullyBookedHotels() {

        Hotel hotel = new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                0,

                "image-url"

        );

        Room queenRoom = new Room("Queen", 2, 180, hotel);

        when(hotelRepository.findByCityIgnoreCase("Seattle"))

                .thenReturn(List.of(hotel));

        when(roomRepository.findByHotelIdAndCapacityGreaterThanEqual(hotel.getId(), 2))

                .thenReturn(List.of(queenRoom));

        when(bookingRepository.existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                any(),

                eq("CONFIRMED"),

                eq(LocalDate.of(2026, 7, 23)),

                eq(LocalDate.of(2026, 7, 20))

        )).thenReturn(true);

        List<HotelSearchResponse> results = hotelService.searchHotels(

                "Seattle",

                LocalDate.of(2026, 7, 20),

                LocalDate.of(2026, 7, 23),

                2

        );

        assertTrue(results.isEmpty());

    }

}