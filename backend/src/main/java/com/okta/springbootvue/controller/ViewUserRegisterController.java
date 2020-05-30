package com.okta.springbootvue.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.UserRegister;
import com.okta.springbootvue.repository.UserRegisterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class ViewUserRegisterController {
    @Autowired
    private final UserRegisterRepository userregisterRepository;

    ViewUserRegisterController(UserRegisterRepository userregisterRepository) {
        this.userregisterRepository = userregisterRepository;
    }

}
