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
import com.okta.springbootvue.repository.CancelReasonRepository;
import com.okta.springbootvue.repository.UserRegisterRepository;

import java.security.cert.CertPathValidatorException.Reason;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class CancelBookingTests {

    private Validator validator;

    @Autowired
    private CancelBookingRepository cancelBookingRepository;
    @Autowired
    private CancelReasonRepository cancelReasonRepository;
    @Autowired
    private UserRegisterRepository userRegisterRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6004408_testMustBeOK() {
        LocalDateTime now = LocalDateTime.now();

        UserRegister u = new UserRegister();
        u.setAnswer("aaaaaaaa");
        u = userRegisterRepository.saveAndFlush(u);

        CancelReason c = new CancelReason();
        c.setReason("ติดธุระ");
        c = cancelReasonRepository.saveAndFlush(c);

        Booking bk = new Booking();
        bk.setBookingTime(now);
        bk = bookingRepository.saveAndFlush(bk);

        CancelBooking b = new CancelBooking();
         b.setBooking(bk);
         b.setReason(c);
         b.setUser(u);
        b.setDate(now);
        b = cancelBookingRepository.saveAndFlush(b);

        Optional<CancelBooking> found = cancelBookingRepository.findById(b.getId());
        assertEquals( b, found.get());
    }



    // @Test
    // void B6004408_testMustNotBeNull(){

    // LocalDateTime now = LocalDateTime.now();
    // UserRegister u = new UserRegister();
    //     u.setAnswer("aaaaaaaa");
    //     u = userRegisterRepository.saveAndFlush(u);

    // CancelReason c = new CancelReason();
    //     c.setReason("ติดธุระ");
    //     c = cancelReasonRepository.saveAndFlush(c);
    
    // Booking bk = new Booking();
    //     bk.setBookingTime(now);
    //     bk = bookingRepository.saveAndFlush(bk);    

    // CancelBooking b = new CancelBooking();
    //     b.setBooking(bk);
    //     b.setReason(c);
    //     b.setUser(u);
    //     b.setDate(now);
    //     b = cancelBookingRepository.saveAndFlush(b);

        
    //     Set<ConstraintViolation<CancelBooking>> result = validator.validate(b);
    //     assertEquals(22222, result.size());

    //     ConstraintViolation<CancelBooking> v = result.iterator().next();
    //     assertEquals("xxxxxxxxxxxxxx", v.getMessage());
    //     assertEquals("asd", v.getPropertyPath().toString());
    //     }


}