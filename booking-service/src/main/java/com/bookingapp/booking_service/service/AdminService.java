package com.bookingapp.booking_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.CreateHotelRequest;
import com.bookingapp.booking_service.dto.CreateRoomRequest;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.repository.HotelRepository;
import com.bookingapp.booking_service.repository.RoomRepository;

@Service

public class AdminService {

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    public AdminService(

            HotelRepository hotelRepository,

            RoomRepository roomRepository

    ) {

        this.hotelRepository = hotelRepository;

        this.roomRepository = roomRepository;

    }

    public List<Hotel> getHotels() {

        return hotelRepository.findAll();

    }

    public Hotel createHotel(CreateHotelRequest request) {

        Hotel hotel = new Hotel(

                request.getName(),

                request.getCity(),

                request.getAddress(),

                request.getRating(),

                0,

                0,

                request.getImageUrl()

        );

        return hotelRepository.save(hotel);

    }

    public Room createRoom(Long hotelId, CreateRoomRequest request) {

        Hotel hotel = hotelRepository.findById(hotelId)

                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        Room room = new Room(

                request.getRoomType(),

                request.getCapacity(),

                request.getPricePerNight(),

                hotel

        );

        return roomRepository.save(room);

    }

}