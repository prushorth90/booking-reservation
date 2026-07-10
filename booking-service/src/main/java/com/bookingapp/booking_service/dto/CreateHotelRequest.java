package com.bookingapp.booking_service.dto;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateHotelRequest {

    @NotBlank(message = "Hotel name is required")

    private String name;

    @NotBlank(message = "City is required")

    private String city;

    @NotBlank(message = "Address is required")

    private String address;

    @Min(value = 0, message = "Rating cannot be below 0")

    @Max(value = 5, message = "Rating cannot be above 5")

    private double rating;

    @NotBlank(message = "Image URL is required")

    private String imageUrl;

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

    public String getImageUrl() {

        return imageUrl;

    }

}