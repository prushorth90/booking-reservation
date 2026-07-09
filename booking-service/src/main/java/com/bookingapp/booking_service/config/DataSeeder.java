package com.bookingapp.booking_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bookingapp.booking_service.model.Hotel;
import com.bookingapp.booking_service.repository.HotelRepository;

@Component

public class DataSeeder implements CommandLineRunner {

    private final HotelRepository hotelRepository;

    public DataSeeder(HotelRepository hotelRepository) {

        this.hotelRepository = hotelRepository;

    }

    @Override

    public void run(String... args) {

        if (hotelRepository.count() > 0) {

            return;

        }

        hotelRepository.save(new Hotel(

                "Downtown Seattle Hotel",

                "Seattle",

                "123 Pike Street",

                4.5,

                180,

                4,

                "https://images.unsplash.com/photo-1566073771259-6a8506099945"

        ));

        hotelRepository.save(new Hotel(

                "Lake View Suites",

                "Seattle",

                "88 Lake Union Ave",

                4.7,

                230,

                2,

                "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa"

        ));

        hotelRepository.save(new Hotel(

                "Airport Business Inn",

                "Seattle",

                "500 Airport Way",

                4.1,

                140,

                6,

                "https://images.unsplash.com/photo-1564501049412-61c2a3083791"

        ));

    }

}