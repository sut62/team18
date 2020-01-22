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
@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class CancelBookingController {
    @Autowired
    private final CancelBookingRepository cancelBookingRepository;
    @Autowired
    private CancelReasonRepository cancelReasonRepository;
    @Autowired
    private UserRegisterRepository userRegisterRepository;
    @Autowired
    private BookingRepository bookingRepository;
   


    CancelBookingController(CancelBookingRepository cancelBookingRepository) {
        this.cancelBookingRepository = cancelBookingRepository;
    }

    @GetMapping("/cancelBooking")
    public Collection<CancelBooking> CancelBookings() {
        return cancelBookingRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/cancelbooking/{book_id}/{reason_id}/{userRegister_id}/{Ans}")
    public CancelBooking newCancelBooking(CancelBooking newCancelBooking,
    @PathVariable long reason_id,
    @PathVariable long book_id,
    @PathVariable long userRegister_id,
    @PathVariable String Ans) {
    UserRegister userregister  = userRegisterRepository.findById(userRegister_id);
    CancelReason cancelreason = cancelReasonRepository.findById(reason_id);
    Booking booking = bookingRepository.findById(book_id);
    LocalDateTime now = LocalDateTime.now();
   

    newCancelBooking.setAns(Ans);
    newCancelBooking.setCancelBy(userregister);
    newCancelBooking.setCancelBook(booking);
    newCancelBooking.setCancelCaused(cancelreason);
    newCancelBooking.setDate(now);
    
   
    return cancelBookingRepository.save(newCancelBooking); 
    }
}