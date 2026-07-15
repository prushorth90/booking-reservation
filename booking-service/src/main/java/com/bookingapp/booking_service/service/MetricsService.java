package com.bookingapp.booking_service.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service

public class MetricsService {

    private final Counter hotelSearchCounter;

    private final Counter hotelSearchNoResultsCounter;

    private final Counter bookingCreatedCounter;

    private final Counter bookingCancelledCounter;

    private final Counter bookingFailedCounter;

    public MetricsService(MeterRegistry meterRegistry) {

        this.hotelSearchCounter = Counter.builder("hotel_search_total")

                .description("Total number of hotel searches")

                .register(meterRegistry);

        this.hotelSearchNoResultsCounter = Counter.builder("hotel_search_no_results_total")

                .description("Total number of hotel searches with no results")

                .register(meterRegistry);

        this.bookingCreatedCounter = Counter.builder("booking_created")

                .description("Total number of successful bookings")

                .register(meterRegistry);

        this.bookingCancelledCounter = Counter.builder("booking_cancelled_total")

                .description("Total number of cancelled bookings")

                .register(meterRegistry);

        this.bookingFailedCounter = Counter.builder("booking_failed_total")

                .description("Total number of failed booking attempts")

                .register(meterRegistry);

    }

    public void incrementHotelSearch() {

        hotelSearchCounter.increment();

    }

    public void incrementHotelSearchNoResults() {

        hotelSearchNoResultsCounter.increment();

    }

    public void incrementBookingCreated() {

        bookingCreatedCounter.increment();

    }

    public void incrementBookingCancelled() {

        bookingCancelledCounter.increment();

    }

    public void incrementBookingFailed() {

        bookingFailedCounter.increment();

    }

}
