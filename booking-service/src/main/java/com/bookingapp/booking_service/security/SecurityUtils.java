package com.bookingapp.booking_service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {

    }

    public static String getCurrentUserEmail() {

        Authentication authentication = SecurityContextHolder

                .getContext()

                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {

            throw new IllegalArgumentException("User is not authenticated");

        }

        return authentication.getName();

    }

    public static boolean currentUserHasRole(String role) {

        Authentication authentication = SecurityContextHolder

                .getContext()

                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {

            return false;

        }

        return authentication.getAuthorities()

                .stream()

                .anyMatch(authority ->

                        authority.getAuthority().equals("ROLE_" + role)

                );

    }

}