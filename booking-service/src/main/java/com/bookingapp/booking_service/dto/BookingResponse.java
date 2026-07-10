package com.bookingapp.booking_service.dto;

import java.time.LocalDate;

public class BookingResponse {

    private Long id;

    private String guestName;

    private String hotelName;

    private String roomType;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String status;

    public BookingResponse(

            Long id,

            String guestName,

            String hotelName,

            String roomType,

            LocalDate checkInDate,

            LocalDate checkOutDate,

            String status

    ) {

        this.id = id;

        this.guestName = guestName;

        this.hotelName = hotelName;

        this.roomType = roomType;

        this.checkInDate = checkInDate;

        this.checkOutDate = checkOutDate;

        this.status = status;

    }

    public Long getId() {

        return id;

    }

    public String getGuestName() {

        return guestName;

    }

    public String getHotelName() {

        return hotelName;

    }

    public String getRoomType() {

        return roomType;

    }

    public LocalDate getCheckInDate() {

        return checkInDate;

    }

    public LocalDate getCheckOutDate() {

        return checkOutDate;

    }

    public String getStatus() {

        return status;

    }

}