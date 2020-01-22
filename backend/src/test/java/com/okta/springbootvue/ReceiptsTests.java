package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.entity.Payment;
import com.okta.springbootvue.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class ReceiptsTests {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6015886_testPaymentTypeOK() { // ใส่ข้อมูลถูกต้องปกติ

        Payment payment = new Payment();
        payment.setName("ชำระเงินสด");
        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment> found = paymentRepository.findById(payment.getId());
        assertEquals("ชำระเงินสด", found.get().getName());
    }

    @Test
    void B6001803_testPaymentTypeMustBeNotNull() { // Payment Type ห้ามว่าง
        Payment payment = new Payment();
        payment.setName(null);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

    @Test
    void B6015886_testPaymentTypeMustBeGreaterEqual10() {
        Payment payment = new Payment();
        payment.setName("123456789");

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        assertEquals("size must be between 10 and 24", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void B6015886_testPaymentTypeMustBeLessEqual24() {
        Payment payment = new Payment();
        payment.setName("1234567890123456789012345");

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        assertEquals("size must be between 10 and 24", result.iterator().next().getMessage());
        assertEquals("type", result.iterator().next().getPropertyPath().toString());
    }

}