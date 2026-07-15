package com.bookingapp.booking_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import com.bookingapp.booking_service.dto.BookingResponse;
import com.bookingapp.booking_service.dto.CreateBookingRequest;
import com.bookingapp.booking_service.model.AppUser;
import com.bookingapp.booking_service.model.Booking;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.model.UserRole;
import com.bookingapp.booking_service.repository.AppUserRepository;
import com.bookingapp.booking_service.repository.BookingRepository;
import com.bookingapp.booking_service.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)

class BookingServiceTest {

    @Mock

    private BookingRepository bookingRepository;

    @Mock

    private RoomRepository roomRepository;

    @Mock

    private AppUserRepository appUserRepository;

    @InjectMocks

    private BookingService bookingService;
     
    @Mock
    private MetricsService metricsService;

    @AfterEach

    void tearDown() {

        SecurityContextHolder.clearContext();

    }

    @Test

    void createBooking_createsBookingForLoggedInUser() {

        setAuthenticatedUser("customer@example.com", "CUSTOMER");

        AppUser user = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        Hotel hotel = new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                0,

                "image-url"

        );

        Room room = new Room("King", 2, 220, hotel);

        ReflectionTestUtils.setField(room, "id", 2L);

        CreateBookingRequest request = new CreateBookingRequest();

        ReflectionTestUtils.setField(request, "roomId", 2L);

        ReflectionTestUtils.setField(request, "guestName", "Solan Mani");

        ReflectionTestUtils.setField(request, "checkInDate", LocalDate.of(2026, 7, 20));

        ReflectionTestUtils.setField(request, "checkOutDate", LocalDate.of(2026, 7, 23));

        when(appUserRepository.findByEmailIgnoreCase("customer@example.com"))

                .thenReturn(Optional.of(user));

        when(roomRepository.findById(2L))

                .thenReturn(Optional.of(room));

        when(bookingRepository.existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                2L,

                "CONFIRMED",

                LocalDate.of(2026, 7, 23),

                LocalDate.of(2026, 7, 20)

        )).thenReturn(false);

        when(bookingRepository.save(any(Booking.class)))

                .thenAnswer(invocation -> invocation.getArgument(0));

        BookingResponse response = bookingService.createBooking(request);

        assertEquals("Solan Mani", response.getGuestName());

        assertEquals("Downtown Seattle Hotel", response.getHotelName());

        assertEquals("King", response.getRoomType());

        assertEquals("CONFIRMED", response.getStatus());

        verify(bookingRepository).save(any(Booking.class));

    }

    @Test

    void createBooking_throwsException_whenRoomAlreadyBooked() {

        setAuthenticatedUser("customer@example.com", "CUSTOMER");

        AppUser user = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        Hotel hotel = new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                0,

                "image-url"

        );

        Room room = new Room("King", 2, 220, hotel);

        ReflectionTestUtils.setField(room, "id", 2L);

        CreateBookingRequest request = new CreateBookingRequest();

        ReflectionTestUtils.setField(request, "roomId", 2L);

        ReflectionTestUtils.setField(request, "guestName", "Solan Mani");

        ReflectionTestUtils.setField(request, "checkInDate", LocalDate.of(2026, 7, 20));

        ReflectionTestUtils.setField(request, "checkOutDate", LocalDate.of(2026, 7, 23));

        when(appUserRepository.findByEmailIgnoreCase("customer@example.com"))

                .thenReturn(Optional.of(user));

        when(roomRepository.findById(2L))

                .thenReturn(Optional.of(room));

        when(bookingRepository.existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                2L,

                "CONFIRMED",

                LocalDate.of(2026, 7, 23),

                LocalDate.of(2026, 7, 20)

        )).thenReturn(true);

        IllegalArgumentException exception = assertThrows(

                IllegalArgumentException.class,

                () -> bookingService.createBooking(request)

        );

        assertEquals("Room is already booked for these dates", exception.getMessage());

        verify(bookingRepository, never()).save(any(Booking.class));

    }

    @Test

    void getBookingsForCurrentUser_returnsOnlyCurrentUserBookings() {

        setAuthenticatedUser("customer@example.com", "CUSTOMER");

        AppUser user = new AppUser(

                "Test Customer",

                "customer@example.com",

                "hashed-password",

                UserRole.CUSTOMER

        );

        Hotel hotel = new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                0,

                "image-url"

        );

        Room room = new Room("Queen", 2, 180, hotel);

        Booking booking = new Booking(

                "Test Guest",

                LocalDate.of(2026, 7, 20),

                LocalDate.of(2026, 7, 23),

                "CONFIRMED",

                room,

                user

        );

        when(bookingRepository.findByUserEmailIgnoreCase("customer@example.com"))

                .thenReturn(List.of(booking));

        List<BookingResponse> results = bookingService.getBookingsForCurrentUser();

        assertEquals(1, results.size());

        assertEquals("Test Guest", results.get(0).getGuestName());

        assertEquals("Downtown Seattle Hotel", results.get(0).getHotelName());

    }

    private void setAuthenticatedUser(String email, String role) {

        UsernamePasswordAuthenticationToken authentication =

                new UsernamePasswordAuthenticationToken(

                        email,

                        null,

                        List.of(new SimpleGrantedAuthority("ROLE_" + role))

                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
