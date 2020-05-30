package com.okta.springbootvue.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Receipts;
import com.okta.springbootvue.repository.ReceiptsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class SearchReceiptsController {
    @Autowired
    private final ReceiptsRepository receiptsRepository;

    SearchReceiptsController(ReceiptsRepository receiptsRepository) {
        this.receiptsRepository = receiptsRepository;
    }

        // ค้นหาจาก id
        @GetMapping("/receipts/searchId={id}")
        List<Receipts> getDetialsReceipts(@PathVariable long id) {
            List<Receipts> re_id = receiptsRepository.findById(id);
            return re_id;
        }

}
