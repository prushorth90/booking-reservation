package com.bookingapp.booking_service.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bookingapp.booking_service.model.AppUser;
import com.bookingapp.booking_service.model.Booking;
import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.model.Room;
import com.bookingapp.booking_service.model.UserRole;
import com.bookingapp.booking_service.repository.AppUserRepository;
import com.bookingapp.booking_service.repository.BookingRepository;
import com.bookingapp.booking_service.repository.HotelRepository;
import com.bookingapp.booking_service.repository.RoomRepository;

@Component

public class DataSeeder implements CommandLineRunner {

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    public DataSeeder(

            HotelRepository hotelRepository,

            RoomRepository roomRepository,

            BookingRepository bookingRepository,

            AppUserRepository appUserRepository,

            PasswordEncoder passwordEncoder

    ) {

        this.hotelRepository = hotelRepository;

        this.roomRepository = roomRepository;

        this.bookingRepository = bookingRepository;

        this.appUserRepository = appUserRepository;

        this.passwordEncoder = passwordEncoder;

    }

    @Override

    public void run(String... args) {

        if (hotelRepository.count() > 0) {

            return;

        }

        AppUser customer = appUserRepository.save(new AppUser(

                "Test Customer",

                "customer@example.com",

                passwordEncoder.encode("password123"),

                UserRole.CUSTOMER

        ));

        AppUser admin = appUserRepository.save(new AppUser(

                "Admin User",

                "admin@example.com",

                passwordEncoder.encode("password123"),

                UserRole.ADMIN

        ));

        Hotel downtown = hotelRepository.save(new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                0,

                "https://images.unsplash.com/photo-1566073771259-6a8506099945"

        ));

        Hotel lakeView = hotelRepository.save(new Hotel(

                "Lake View Suites",

                "Seattle",

                "88 Lake Union Ave",

                4.7,

                230,

                0,

                "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa"

        ));

        Hotel airport = hotelRepository.save(new Hotel(

                "Airport Business Inn",

                "Seattle",

                "500 Airport Way",

                4.1,

                140,

                0,

                "https://images.unsplash.com/photo-1564501049412-61c2a3083791"

        ));

        Room downtownQueen = roomRepository.save(new Room(

                "Queen",

                2,

                180,

                downtown

        ));

        roomRepository.save(new Room(

                "King",

                2,

                220,

                downtown

        ));

        roomRepository.save(new Room(

                "Family Suite",

                4,

                300,

                downtown

        ));

        roomRepository.save(new Room(

                "Lake Queen",

                2,

                230,

                lakeView

        ));

        roomRepository.save(new Room(

                "Lake Suite",

                4,

                350,

                lakeView

        ));

        roomRepository.save(new Room(

                "Business Queen",

                2,

                140,

                airport

        ));

        roomRepository.save(new Room(

                "Business King",

                2,

                160,

                airport

        ));

        bookingRepository.save(new Booking(

                "Test Guest",

                LocalDate.of(2026, 7, 20),

                LocalDate.of(2026, 7, 23),

                "CONFIRMED",

                downtownQueen,

                customer

        ));

    }

}