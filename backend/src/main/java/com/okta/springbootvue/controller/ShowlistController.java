package com.okta.springbootvue.controller;
import com.okta.springbootvue.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class ShowlistController {
    @Autowired
    private final ShowRepository showRepository;

    ShowlistController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

}