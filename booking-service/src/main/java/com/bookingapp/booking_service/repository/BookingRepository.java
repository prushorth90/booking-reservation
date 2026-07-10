package com.bookingapp.booking_service.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapp.booking_service.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

            Long roomId,

            String status,

            LocalDate checkOut,

            LocalDate checkIn

    );

}