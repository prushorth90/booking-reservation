package com.bookingapp.booking_service.service;

import java.time.LocalDate;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.HotelSearchResponse;

import com.bookingapp.booking_service.model.Hotel;

import com.bookingapp.booking_service.model.Room;

import com.bookingapp.booking_service.repository.BookingRepository;

import com.bookingapp.booking_service.repository.HotelRepository;

import com.bookingapp.booking_service.repository.RoomRepository;

@Service

public class HotelService {

    private static final Logger logger =

            LoggerFactory.getLogger(HotelService.class);

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    private final MetricsService metricsService;

    public HotelService(

            HotelRepository hotelRepository,

            RoomRepository roomRepository,

            BookingRepository bookingRepository,

            MetricsService metricsService

    ) {

        this.hotelRepository = hotelRepository;

        this.roomRepository = roomRepository;

        this.bookingRepository = bookingRepository;

        this.metricsService = metricsService;

    }

    @Cacheable(

            cacheNames = "hotelSearch",

            key = "T(String).format('%s:%s:%s:%s', " +

                    "#location.toLowerCase(), " +

                    "#checkIn, " +

                    "#checkOut, " +

                    "#guests)"

    )

    public List<HotelSearchResponse> searchHotels(

            String location,

            LocalDate checkIn,

            LocalDate checkOut,

            int guests

    ) {

        if (location == null || location.isBlank()) {

            throw new IllegalArgumentException("Location is required");

        }

        if (checkIn == null || checkOut == null) {

            throw new IllegalArgumentException("Check in and check out dates are required");

        }

        if (!checkOut.isAfter(checkIn)) {

            throw new IllegalArgumentException("Check out date must be after check in date");

        }

        if (guests < 1) {

            throw new IllegalArgumentException("Guests must be at least 1");

        }

        metricsService.incrementHotelSearch();

        logger.info(

                "hotel_search_database_query location={} checkIn={} checkOut={} guests={}",

                location,

                checkIn,

                checkOut,

                guests

        );

        List<Hotel> hotels =

                hotelRepository.findByCityIgnoreCase(location.trim());

        List<HotelSearchResponse> results = hotels.stream()

                .map(hotel -> buildHotelSearchResponse(

                        hotel,

                        checkIn,

                        checkOut,

                        guests

                ))

                .filter(result -> result != null)

                .toList();

        if (results.isEmpty()) {

            metricsService.incrementHotelSearchNoResults();

            logger.info(

                    "hotel_search_no_results location={} checkIn={} checkOut={} guests={}",

                    location,

                    checkIn,

                    checkOut,

                    guests

            );

        } else {

            logger.info(

                    "hotel_search_completed location={} checkIn={} checkOut={} guests={} resultCount={}",

                    location,

                    checkIn,

                    checkOut,

                    guests,

                    results.size()

            );

        }

        return results;

    }

    private HotelSearchResponse buildHotelSearchResponse(

            Hotel hotel,

            LocalDate checkIn,

            LocalDate checkOut,

            int guests

    ) {

        List<Room> candidateRooms =

                roomRepository.findByHotelIdAndCapacityGreaterThanEqual(

                        hotel.getId(),

                        guests

                );

        List<Room> availableRooms = candidateRooms.stream()

                .filter(room -> !bookingRepository

                        .existsByRoomIdAndStatusAndCheckInDateLessThanAndCheckOutDateGreaterThan(

                                room.getId(),

                                "CONFIRMED",

                                checkOut,

                                checkIn

                        ))

                .toList();

        if (availableRooms.isEmpty()) {

            return null;

        }

        Room cheapestRoom = availableRooms.stream()

                .min((room1, room2) ->

                        Integer.compare(

                                room1.getPricePerNight(),

                                room2.getPricePerNight()

                        )

                )

                .orElseThrow();

        return new HotelSearchResponse(

                hotel.getId(),

                hotel.getName(),

                hotel.getCity(),

                hotel.getAddress(),

                hotel.getRating(),

                cheapestRoom.getPricePerNight(),

                availableRooms.size(),

                hotel.getImageUrl(),

                cheapestRoom.getId(),

                cheapestRoom.getRoomType()

        );

    }

}