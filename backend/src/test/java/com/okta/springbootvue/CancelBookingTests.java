package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class CancelBookingTests {

    private Validator validator;

    @Autowired
    private CancelBookingRepository cancelBookingRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6004408_testMustBeOK() {
        LocalDateTime now = LocalDateTime.now();

        CancelBooking b = new CancelBooking();
        b.setAns("ssssssssss");
        b.setDate(now);
        b = cancelBookingRepository.saveAndFlush(b);

        Optional<CancelBooking> found = cancelBookingRepository.findById(b.getId());
        assertEquals( b, found.get());
    }


@Test

void B6004408_testMustBeNull(){
    LocalDateTime now = LocalDateTime.now();

    CancelBooking b = new CancelBooking();
    b.setAns(null);
    b.setDate(now);
    

    Set<ConstraintViolation<CancelBooking>> result = validator.validate(b);
        assertEquals(1, result.size());

    ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }



    @Test
    void B6004408_testMustBeMin(){
    LocalDateTime now = LocalDateTime.now();

    CancelBooking b = new CancelBooking();
    b.setAns("w");
    b.setDate(now);
    
    Set<ConstraintViolation<CancelBooking>> result = validator.validate(b);
        assertEquals(1, result.size());

    ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("ERROR MIN", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test

    void B6004408_testMustBeMax(){
        LocalDateTime now = LocalDateTime.now();
    
        CancelBooking b = new CancelBooking();
        b.setAns("MAXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        b.setDate(now);
            
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(b);
            assertEquals(1, result.size());
    
        ConstraintViolation<CancelBooking> v = result.iterator().next();
            assertEquals("ERROR MAX", v.getMessage());
            assertEquals("Ans", v.getPropertyPath().toString());
        }



}
