package com.bookingapp.booking_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Booking() {
    }

    public Booking(
            String guestName,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            String status,
            Room room,
            AppUser user
    ) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.room = room;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
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

    public Room getRoom() {
        return room;
    }

    public AppUser getUser() {
        return user;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}