package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.Ratingshow;
import com.okta.springbootvue.repository.RatingshowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class RatingshowController {

    @Autowired
    private final RatingshowRepository ratingshowRepository;

    public RatingshowController(RatingshowRepository ratingshowRepository) {
        this.ratingshowRepository = ratingshowRepository;
    }

    @GetMapping("/ratingshow")
    public Collection<Ratingshow> Ratingshows() {
        return ratingshowRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/ratingshow/{id}")
    public Optional<Ratingshow> Ratingshows(@PathVariable Long id) {
        Optional<Ratingshow> ratingshow = ratingshowRepository.findById(id);
        return ratingshow;
    }

}