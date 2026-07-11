package com.bookingapp.booking_service.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class AppUser {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    @Column(unique = true, nullable = false)

    private String email;

    private String passwordHash;

    @Enumerated(EnumType.STRING)

    private UserRole role;

    public AppUser() {

    }

    public AppUser(String name, String email, String passwordHash, UserRole role) {

        this.name = name;

        this.email = email;

        this.passwordHash = passwordHash;

        this.role = role;

    }

    public Long getId() {

        return id;

    }

    public String getName() {

        return name;

    }

    public String getEmail() {

        return email;

    }

    public String getPasswordHash() {

        return passwordHash;

    }

    public UserRole getRole() {

        return role;

    }

}