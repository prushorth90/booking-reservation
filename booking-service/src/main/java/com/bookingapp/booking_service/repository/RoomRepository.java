package com.bookingapp.booking_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookingapp.booking_service.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotelIdAndCapacityGreaterThanEqual(Long hotelId, int capacity);

}