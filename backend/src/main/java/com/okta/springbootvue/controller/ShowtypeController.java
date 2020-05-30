package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.Showtype;
import com.okta.springbootvue.repository.ShowtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class ShowtypeController {

    @Autowired
    private final ShowtypeRepository showtypeRepository;

    public ShowtypeController(ShowtypeRepository showtypeRepository) {
        this.showtypeRepository = showtypeRepository;
    }

    @GetMapping("/showtype")
    public Collection<Showtype> Showtypes() {
        return showtypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/showtype/{id}")
    public Optional<Showtype> Showtypes(@PathVariable Long id) {
        Optional<Showtype> showtype = showtypeRepository.findById(id);
        return showtype;
    }

}
