package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Zone;
import com.okta.springbootvue.repository.ZoneRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class ZoneController {

    @Autowired
    private final ZoneRepository zoneRepository;

    public ZoneController(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @GetMapping("/zone")
    public Collection<Zone> Zones() {
        return zoneRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/zone/{id}")
    public Optional<Zone> Zones_id(@PathVariable Long id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        return zone;
    }

}
