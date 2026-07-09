package com.bookingapp.booking_service.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bookingapp.booking_service.dto.HotelSearchResponse;

@Service

public class HotelService {
public List<HotelSearchResponse> searchHotels(

        String location,

        LocalDate checkIn,

        LocalDate checkOut,

        int guests

) {

    if (!checkOut.isAfter(checkIn)) {

        throw new IllegalArgumentException("Check out date must be after check in date");

    }

    return List.of(

            new HotelSearchResponse(

                    1L,

                    "Downtown Seattle Hotel",

                    "Seattle",

                    "123 Pike Street",

                    4.5,

                    180,

                    4,

                    "https://images.unsplash.com/photo-1566073771259-6a8506099945"

            ),

            new HotelSearchResponse(

                    2L,

                    "Lake View Suites",

                    "Seattle",

                    "88 Lake Union Ave",

                    4.7,

                    230,

                    2,

                    "https://images.unsplash.com/photo-1551882547-ff40c63fe5fa"

            ),

            new HotelSearchResponse(

                    3L,

                    "Airport Business Inn",

                    "Seattle",

                    "500 Airport Way",

                    4.1,

                    140,

                    6,

                    "https://images.unsplash.com/photo-1564501049412-61c2a3083791"

            )

    );

}
     
}