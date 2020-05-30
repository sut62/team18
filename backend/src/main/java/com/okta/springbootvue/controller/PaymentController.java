package com.okta.springbootvue.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Payment;
import com.okta.springbootvue.repository.PaymentRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.201:8082")
@RestController
public class PaymentController {

    @Autowired
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payment")
    public Collection<Payment> Payments() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

}
