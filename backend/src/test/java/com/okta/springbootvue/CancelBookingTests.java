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
        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // set คำตอบที่ถูก
        cancelbook.setAns("ssssssssss");
        cancelbook.setDate(now);
        // บันทึกค่า
        cancelbook = cancelBookingRepository.saveAndFlush(cancelbook);
        // เทียบค่าที่บันทึก กับค่าที่ส่งไป
        Optional<CancelBooking> found = cancelBookingRepository.findById(cancelbook.getId());
        assertEquals(cancelbook, found.get());
    }

    @Test

    void B6004408_testMustBeNull() {
        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // set null
        cancelbook.setAns(null);
        cancelbook.setDate(now);
        // เปรียบเทียบไซส์
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_testMustBeMin() {
        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // set ค่า1ตัว
        cancelbook.setAns("w");
        cancelbook.setDate(now);
        // เทียบค่าที่บันทึก
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("ERROR MIN", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test

    void B6004408_testMustBeMax() {
        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // setค่ามากที่สุด
        cancelbook.setAns("0123456789012345678901234567890123456789");
        cancelbook.setDate(now);
        // เทียบค่าที่บันทึก
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("ERROR MAX", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_testPattern() {
        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // setค่า
        cancelbook.setAns("asdasdกขคงะา ิ ี ุ ู");
        cancelbook.setDate(now);
        // เทียบค่า
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("Wrong Pattern", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_DateintehPastorPresent() {
        // สร้าง object cancelBook
        CancelBooking cancelBook = new CancelBooking();
        // ใส่ค่าที่เรากำหนดไว้
        LocalDateTime lDate = LocalDateTime.parse("2030-11-03T12:45:30");
        // ใส่ค่าที่เรากำหนดไว้
        cancelBook.setDate(lDate);
        cancelBook.setAns("abcdef");

        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelBook);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }






}
