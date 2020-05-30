package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Seat;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.201:8082")
@RestController
public class SeatController {

    @Autowired
    private final SeatRepository seatRepository;
    @Autowired
    private final ZoneRepository zoneRepository;

    public SeatController(SeatRepository seatRepository, ZoneRepository zoneRepository) {
        this.seatRepository = seatRepository;
        this.zoneRepository = zoneRepository;
    }

    @GetMapping("/seat")
    public Collection<Seat> Seats() {
        return seatRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/seat/zoneid={zone}")
    public Collection<Seat> getSeatByZone(@PathVariable("zone") Long zone) {
        return seatRepository.findSeatByZone(zone);
    }

    @GetMapping("/seat/{id}")
    public Optional<Seat> Seats_id(@PathVariable Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        return seat;
    }

}
