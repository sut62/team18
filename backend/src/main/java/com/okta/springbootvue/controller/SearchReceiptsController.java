package com.okta.springbootvue.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Receipts;
import com.okta.springbootvue.repository.ReceiptsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class SearchReceiptsController {
    @Autowired
    private final ReceiptsRepository receiptsRepository;

    SearchReceiptsController(ReceiptsRepository receiptsRepository) {
        this.receiptsRepository = receiptsRepository;
    }

    @GetMapping("/searchReceipts")
    public Collection<Receipts> Receipts() {
        return receiptsRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/receipts/{id}")
    public Optional<Receipts> Receiptss(@PathVariable Long id) {
        Optional<Receipts> receipts = receiptsRepository.findById(id);
        return receipts;
    }

}