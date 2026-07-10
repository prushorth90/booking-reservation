package com.bookingapp.booking_service.dto;

public class HotelSearchResponse {

    private Long id;

    private String name;

    private String city;

    private String address;

    private double rating;

    private int pricePerNight;

    private int availableRooms;

    private String imageUrl;

    private Long roomId;

    private String roomType;

    public HotelSearchResponse(

            Long id,

            String name,

            String city,

            String address,

            double rating,

            int pricePerNight,

            int availableRooms,

            String imageUrl,

            Long roomId,

            String roomType

    ) {

        this.id = id;

        this.name = name;

        this.city = city;

        this.address = address;

        this.rating = rating;

        this.pricePerNight = pricePerNight;

        this.availableRooms = availableRooms;

        this.imageUrl = imageUrl;

        this.roomId = roomId;

        this.roomType = roomType;

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

    public Long getRoomId() {

        return roomId;

    }

    public String getRoomType() {

        return roomType;

    }

}