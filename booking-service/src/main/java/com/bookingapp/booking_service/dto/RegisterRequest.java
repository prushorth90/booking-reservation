package com.bookingapp.booking_service.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Name is required")

    private String name;

    @Email(message = "Email must be valid")

    @NotBlank(message = "Email is required")

    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")

    @NotBlank(message = "Password is required")

    private String password;

    public String getName() {

        return name;

    }

    public String getEmail() {

        return email;

    }

    public String getPassword() {

        return password;

    }

}
