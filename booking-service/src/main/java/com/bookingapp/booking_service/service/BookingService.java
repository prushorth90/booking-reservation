package com.bookingapp.booking_service.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.BookingResponse;

import com.bookingapp.booking_service.dto.CreateBookingRequest;

import com.bookingapp.booking_service.model.AppUser;

import com.bookingapp.booking_service.model.Booking;

import com.bookingapp.booking_service.model.Room;

import com.bookingapp.booking_service.repository.AppUserRepository;

import com.bookingapp.booking_service.repository.BookingRepository;

import com.bookingapp.booking_service.repository.RoomRepository;

import com.bookingapp.booking_service.security.SecurityUtils;

@Service

public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final AppUserRepository appUserRepository;

    private final MetricsService metricsService;

    public BookingService(

            BookingRepository bookingRepository,

            RoomRepository roomRepository,

            AppUserRepository appUserRepository,

            MetricsService metricsService

    ) {

        this.bookingRepository = bookingRepository;

        this.roomRepository = roomRepository;

        this.appUserRepository = appUserRepository;

        this.metricsService = metricsService;

    }

    @CacheEvict(

            cacheNames = "hotelSearch",

            allEntries = true

    )

    public BookingResponse createBooking(CreateBookingRequest request) {

        try {

            if (request.getCheckInDate() == null

                    || request.getCheckOutDate() == null) {

                throw new IllegalArgumentException(

                        "Check in and check out dates are required"

                );

            }

            if (!request.getCheckOutDate()

                    .isAfter(request.getCheckInDate())) {

                throw new IllegalArgumentException(

                        "Check out date must be after check in date"

                );

            }

            String currentUserEmail =

                    SecurityUtils.getCurrentUserEmail();

            AppUser user = appUserRepository

                    .findByEmailIgnoreCase(currentUserEmail)

                    .orElseThrow(() ->

                            new IllegalArgumentException("User not found")

                    );

            Room room = roomRepository

                    .findById(request.getRoomId())

                    .orElseThrow(() ->

                            new IllegalArgumentException("Room not found")

                    );

            boolean alreadyBooked =

                    bookingRepository

                            .existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                                    room.getId(),

                                    "CONFIRMED",

                                    request.getCheckOutDate(),

                                    request.getCheckInDate()

                            );

            if (alreadyBooked) {

                throw new IllegalArgumentException(

                        "Room is already booked for these dates"

                );

            }

            Booking booking = new Booking(

                    request.getGuestName(),

                    request.getCheckInDate(),

                    request.getCheckOutDate(),

                    "CONFIRMED",

                    room,

                    user

            );

            Booking savedBooking =

                    bookingRepository.save(booking);

            metricsService.incrementBookingCreated();

            return toBookingResponse(savedBooking);

        } catch (RuntimeException exception) {

            metricsService.incrementBookingFailed();

            throw exception;

        }

    }

    public List<BookingResponse> getBookingsForCurrentUser() {

        String currentUserEmail =

                SecurityUtils.getCurrentUserEmail();

        return bookingRepository

                .findByUserEmailIgnoreCase(currentUserEmail)

                .stream()

                .map(this::toBookingResponse)

                .toList();

    }

    public List<BookingResponse> getAllBookingsForAdmin() {

        return bookingRepository

                .findAll()

                .stream()

                .map(this::toBookingResponse)

                .toList();

    }

    @CacheEvict(

            cacheNames = "hotelSearch",

            allEntries = true

    )

    public BookingResponse cancelBooking(Long bookingId) {

        String currentUserEmail =

                SecurityUtils.getCurrentUserEmail();

        Booking booking = bookingRepository

                .findById(bookingId)

                .orElseThrow(() ->

                        new IllegalArgumentException("Booking not found")

                );

        boolean isOwner = booking

                .getUser()

                .getEmail()

                .equalsIgnoreCase(currentUserEmail);

        boolean isAdmin =

                SecurityUtils.currentUserHasRole("ADMIN");

        if (!isOwner && !isAdmin) {

            throw new IllegalArgumentException(

                    "You are not allowed to cancel this booking"

            );

        }

        if ("CANCELLED".equals(booking.getStatus())) {

            throw new IllegalArgumentException(

                    "Booking is already cancelled"

            );

        }

        booking.setStatus("CANCELLED");

        Booking savedBooking =

                bookingRepository.save(booking);

        metricsService.incrementBookingCancelled();

        return toBookingResponse(savedBooking);

    }

    private BookingResponse toBookingResponse(Booking booking) {

        return new BookingResponse(

                booking.getId(),

                booking.getGuestName(),

                booking.getRoom().getHotel().getName(),

                booking.getRoom().getRoomType(),

                booking.getCheckInDate(),

                booking.getCheckOutDate(),

                booking.getStatus()

        );

    }

}