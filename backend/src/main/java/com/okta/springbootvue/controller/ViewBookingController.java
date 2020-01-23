package com.okta.springbootvue.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Booking;
import com.okta.springbootvue.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class ViewBookingController {
    @Autowired
    private final BookingRepository bookingRepository;

    ViewBookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}