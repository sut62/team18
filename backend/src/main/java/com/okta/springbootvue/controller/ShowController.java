package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Show;
import com.okta.springbootvue.entity.Employee;
import com.okta.springbootvue.entity.Ratingshow;
import com.okta.springbootvue.entity.Showtype;
import com.okta.springbootvue.repository.ShowRepository;
import com.okta.springbootvue.repository.EmployeeRepository;
import com.okta.springbootvue.repository.RatingshowRepository;
import com.okta.springbootvue.repository.ShowtypeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.201:8082")
@RestController
public class ShowController {
    @Autowired
    private final ShowRepository showRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RatingshowRepository ratingshowRepository;
    @Autowired
    private ShowtypeRepository showtypeRepository;

    ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping("/show")
    public Collection<Show> Shows() {
        return showRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/show/showid={id}")
    public Optional<Show> Shows_id(@PathVariable Long id) {
        Optional<Show> show = showRepository.findById(id);
        return show;
    }

    @PostMapping("/show/{actor}/{employee_id}/{information}/{ratingshow_id}/{showtype_id}/{title}")
    public Show newShow(Show newShow,
    @PathVariable String actor,
    @PathVariable long employee_id,
    @PathVariable String information,
    @PathVariable long ratingshow_id,
    @PathVariable long showtype_id,
    @PathVariable String title) {
    
    Employee employee = employeeRepository.findById(employee_id);
    Ratingshow ratingshow = ratingshowRepository.findById(ratingshow_id);
    Showtype showtype = showtypeRepository.findById(showtype_id);
    
    newShow.setEmployee(employee);
    newShow.setShowtype(showtype);
    newShow.setRatingshow(ratingshow);
    newShow.setTitle(title);
    newShow.setActor(actor);
    newShow.setInformation(information);
    return showRepository.save(newShow); 
    
    }
}
