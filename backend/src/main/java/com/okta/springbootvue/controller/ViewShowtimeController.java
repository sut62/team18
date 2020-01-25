package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.okta.springbootvue.entity.Showtime;
import com.okta.springbootvue.repository.ShowtimeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class ViewShowtimeController {
    @Autowired
    private final ShowtimeRepository showtimeRepository;
    
    

    ViewShowtimeController(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

   @GetMapping("/showtime")
    public Collection<Showtime> Showtimes() {
        return showtimeRepository.findAll().stream().collect(Collectors.toList());
    }

   
}
