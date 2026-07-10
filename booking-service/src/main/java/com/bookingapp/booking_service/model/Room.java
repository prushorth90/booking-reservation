package com.bookingapp.booking_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Room {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String roomType;

    private int capacity;

    private int pricePerNight;

    @ManyToOne

    @JoinColumn(name = "hotel_id")

    private Hotel hotel;

    public Room() {

    }

    public Room(String roomType, int capacity, int pricePerNight, Hotel hotel) {

        this.roomType = roomType;

        this.capacity = capacity;

        this.pricePerNight = pricePerNight;

        this.hotel = hotel;

    }

    public Long getId() {

        return id;

    }

    public String getRoomType() {

        return roomType;

    }

    public int getCapacity() {

        return capacity;

    }

    public int getPricePerNight() {

        return pricePerNight;

    }

    public Hotel getHotel() {

        return hotel;

    }

}
