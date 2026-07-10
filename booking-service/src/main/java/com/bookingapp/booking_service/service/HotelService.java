package com.bookingapp.booking_service.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.HotelSearchResponse;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.repository.BookingRepository;
import com.bookingapp.booking_service.repository.HotelRepository;
import com.bookingapp.booking_service.repository.RoomRepository;

@Service

public class HotelService {

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    public HotelService(

            HotelRepository hotelRepository,

            RoomRepository roomRepository,

            BookingRepository bookingRepository

    ) {

        this.hotelRepository = hotelRepository;

        this.roomRepository = roomRepository;

        this.bookingRepository = bookingRepository;

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

                .map(hotel -> {

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

                })

                .filter(result -> result != null)

                .toList();

    }

}