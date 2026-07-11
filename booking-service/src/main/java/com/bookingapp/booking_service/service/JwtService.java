package com.bookingapp.booking_service.service;

import com.bookingapp.booking_service.model.AppUser;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import java.util.Date;

@Service

public class JwtService {

    @Value("${app.jwt.secret}")

    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")

    private long jwtExpirationMs;

    public String generateToken(AppUser user) {

        Date now = new Date();

        Date expiresAt = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()

                .subject(user.getEmail())

                .claim("userId", user.getId())

                .claim("name", user.getName())

                .claim("role", user.getRole().name())

                .issuedAt(now)

                .expiration(expiresAt)

                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))

                .compact();

    }

}
