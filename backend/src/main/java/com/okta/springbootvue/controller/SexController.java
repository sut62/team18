package com.okta.springbootvue.controller;
import com.okta.springbootvue.entity.Sex;
import com.okta.springbootvue.repository.SexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class SexController {
 @Autowired
    private final SexRepository sexRepository;

    public SexController(SexRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    @GetMapping("/sex")
    public Collection<Sex> Sex() {
        return sexRepository.findAll().stream().collect(Collectors.toList());
    } 

    
}

