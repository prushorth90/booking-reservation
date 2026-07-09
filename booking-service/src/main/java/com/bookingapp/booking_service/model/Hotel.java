package com.bookingapp.booking_service.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String address;
    private double rating;
    private int pricePerNight;
    private int availableRooms;
    private String imageUrl;

    public Hotel() {
    }

    public Hotel(
            String name,
            String city,
            String address,
            double rating,
            int pricePerNight,
            int availableRooms,
            String imageUrl
    ) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}