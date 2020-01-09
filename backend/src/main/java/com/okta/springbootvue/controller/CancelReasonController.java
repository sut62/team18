
package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.CancelReason;
import com.okta.springbootvue.repository.CancelReasonRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class CancelReasonController {

    @Autowired
    private final CancelReasonRepository cancelReasonRepository;

    public CancelReasonController(CancelReasonRepository cancelReasonRepository) {
        this.cancelReasonRepository = cancelReasonRepository;
    }

    @GetMapping("/cancelReason")
    public Collection<CancelReason> Employees() {
        return cancelReasonRepository.findAll().stream().collect(Collectors.toList());
    }

}