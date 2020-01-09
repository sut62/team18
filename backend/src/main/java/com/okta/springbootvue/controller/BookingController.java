package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.Booking;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class BookingController {
    @Autowired
    private final BookingRepository bookingRepository;
    @Autowired
    private UserRegisterRepository userRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private TimeRepository timeRepository;

    BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/booking")
    public Collection<Booking> Bookings() {
        return bookingRepository.findAll().stream().collect(Collectors.toList());
    }

    // check seat
    @GetMapping("/booking/showtime={showtime}/seat={seat}")
    public Collection<Booking> getCheckSeat(@PathVariable("showtime") Long showtime, @PathVariable("seat") Long seat) {
        return bookingRepository.checkSeat(showtime, seat);
    }

    // save booking
    @PostMapping("/booking/success/{user_id}-{showtime_id}-{seat_id}-{time_id}")
    public Booking newBooking(Booking newBooking, @PathVariable long user_id, @PathVariable long showtime_id,
            @PathVariable long seat_id,@PathVariable long time_id) {

        UserRegister chooseUser = userRepository.findById(user_id);
        Showtime chooseShowtime = showtimeRepository.findById(showtime_id);
        Seat chooseSeat = seatRepository.findById(seat_id);
        Time time = timeRepository.findById(time_id);
        LocalDateTime booking_time = LocalDateTime.now();

        newBooking.setChooseUser(chooseUser); // user
        newBooking.setChooseShowtime(chooseShowtime); // showtime
        newBooking.setChooseSeat(chooseSeat); // seat
        newBooking.setBookingTime(booking_time); // set date
        newBooking.setTime(time);
        bookingRepository.changeSeatStatus(seat_id);

        return bookingRepository.save(newBooking); // บันทึก Objcet ชื่อ Booking
    }

}