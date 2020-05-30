package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.*;

import com.okta.springbootvue.entity.Booking;
import com.okta.springbootvue.entity.CancelBooking;
import com.okta.springbootvue.entity.CancelReason;
import com.okta.springbootvue.entity.Question;
import com.okta.springbootvue.entity.UserRegister;

import com.okta.springbootvue.repository.CancelBookingRepository;
import com.okta.springbootvue.repository.CancelReasonRepository;
import com.okta.springbootvue.repository.QuestionRepository;
import com.okta.springbootvue.repository.UserRegisterRepository;
import com.okta.springbootvue.repository.BookingRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class ViewCancelBookingController {
    @Autowired
    private final CancelBookingRepository cancelBookingRepository;
   


    ViewCancelBookingController(CancelBookingRepository cancelBookingRepository) {
        this.cancelBookingRepository = cancelBookingRepository;
    }

    @GetMapping("/ViewCancel")
    public Collection<CancelBooking> CancelBookings() {
        return cancelBookingRepository.findAll().stream().collect(Collectors.toList());
    }

   
    }
