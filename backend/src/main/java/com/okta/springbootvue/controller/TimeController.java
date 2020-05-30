package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.Time;
import com.okta.springbootvue.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class TimeController {

    @Autowired
    private final TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping("/time")
    public Collection<Time> Times() {
        return timeRepository.findAll().stream().collect(Collectors.toList());
    }

 

}
