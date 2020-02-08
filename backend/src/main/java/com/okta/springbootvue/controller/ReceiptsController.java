package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import com.okta.springbootvue.entity.Receipts;
import com.okta.springbootvue.entity.Payment;
import com.okta.springbootvue.entity.Employee;
import com.okta.springbootvue.entity.Booking;
import com.okta.springbootvue.repository.ReceiptsRepository;
import com.okta.springbootvue.repository.PaymentRepository;
import com.okta.springbootvue.repository.EmployeeRepository;
import com.okta.springbootvue.repository.BookingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class ReceiptsController {
    @Autowired
    private final ReceiptsRepository receiptsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    ReceiptsController(ReceiptsRepository receiptsRepository) {
        this.receiptsRepository = receiptsRepository;
    }

    @GetMapping("/receipts")
    public Collection<Receipts> Receipts() {
        return receiptsRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/receipts/{employee_id}/{booking_id}/{payment_id}/{note}")
    public Receipts newReceipts(Receipts newReceipts, @PathVariable long employee_id,  
    @PathVariable long booking_id, @PathVariable long payment_id, @PathVariable String note) {
        
    Employee employee = employeeRepository.findById(employee_id);	
    Booking booking = bookingRepository.findById(booking_id);
    Payment payment = paymentRepository.findById(payment_id);
    
    ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
    ZonedDateTime zdt = ZonedDateTime.now( z ) ;
    LocalDateTime receipts_datetime = zdt.toLocalDateTime();

    newReceipts.setCreatedBy(employee);
    newReceipts.setBooking(booking);
    newReceipts.setPayment(payment);
    newReceipts.setReceiptsDatetime(receipts_datetime);
    newReceipts.setNote(note);

    return receiptsRepository.save(newReceipts); 
    }
}