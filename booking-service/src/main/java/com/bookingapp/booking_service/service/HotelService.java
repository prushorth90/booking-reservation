package com.bookingapp.booking_service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.HotelSearchResponse;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.repository.HotelRepository;

@Service

public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {

        this.hotelRepository = hotelRepository;

    }

    public List<HotelSearchResponse> searchHotels(

            String location,

            LocalDate checkIn,

            LocalDate checkOut,

            int guests

    ) {

        if (!checkOut.isAfter(checkIn)) {

            throw new IllegalArgumentException("Check out date must be after check in date");

        }

        List<Hotel> hotels = hotelRepository.findByCityIgnoreCase(location);

        return hotels.stream()

                .map(hotel -> new HotelSearchResponse(

                        hotel.getId(),

                        hotel.getName(),

                        hotel.getCity(),

                        hotel.getAddress(),

                        hotel.getRating(),

                        hotel.getPricePerNight(),

                        hotel.getAvailableRooms(),

                        hotel.getImageUrl()

                ))

                .toList();

    }

}