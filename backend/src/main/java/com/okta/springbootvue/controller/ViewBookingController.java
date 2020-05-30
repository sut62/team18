package com.okta.springbootvue.controller;

import java.util.Collection;

import com.okta.springbootvue.entity.Booking;
import com.okta.springbootvue.repository.ViewBookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "172.17.0.201:8082")
@RestController
public class ViewBookingController {
    @Autowired
    private final ViewBookingRepository viewBookingRepository;

    ViewBookingController(ViewBookingRepository viewBookingRepository) {
        this.viewBookingRepository = viewBookingRepository;
    }

    // get booking by user
    @GetMapping("/bookingby/{user}")
    public Collection<Booking> getBookingByUser(@PathVariable("user") Long user) {
        return viewBookingRepository.findBookingByUser(user);
    }

}
