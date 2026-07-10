package com.bookingapp.booking_service.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.BookingResponse;
import com.bookingapp.booking_service.dto.CreateBookingRequest;
import com.bookingapp.booking_service.model.Booking;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.repository.BookingRepository;
import com.bookingapp.booking_service.repository.RoomRepository;
@Service

public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    public BookingService(

            BookingRepository bookingRepository,

            RoomRepository roomRepository

    ) {

        this.bookingRepository = bookingRepository;

        this.roomRepository = roomRepository;

    }

    public BookingResponse createBooking(CreateBookingRequest request) {

        if (!request.getCheckOutDate().isAfter(request.getCheckInDate())) {

            throw new IllegalArgumentException("Check out date must be after check in date");

        }

        Room room = roomRepository.findById(request.getRoomId())

                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        boolean alreadyBooked =

                bookingRepository.existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                        room.getId(),

                        "CONFIRMED",

                        request.getCheckOutDate(),

                        request.getCheckInDate()

                );

        if (alreadyBooked) {

            throw new IllegalArgumentException("Room is already booked for these dates");

        }

        Booking booking = new Booking(

                request.getGuestName(),

                request.getCheckInDate(),

                request.getCheckOutDate(),

                "CONFIRMED",

                room

        );

        Booking savedBooking = bookingRepository.save(booking);

        return new BookingResponse(

                savedBooking.getId(),

                savedBooking.getGuestName(),

                room.getHotel().getName(),

                room.getRoomType(),

                savedBooking.getCheckInDate(),

                savedBooking.getCheckOutDate(),

                savedBooking.getStatus()

        );

    }

    public List<BookingResponse> getAllBookings() {

    return bookingRepository.findAll()

            .stream()

            .map(booking -> new BookingResponse(

                    booking.getId(),

                    booking.getGuestName(),

                    booking.getRoom().getHotel().getName(),

                    booking.getRoom().getRoomType(),

                    booking.getCheckInDate(),

                    booking.getCheckOutDate(),

                    booking.getStatus()

            ))

            .toList();

}

}