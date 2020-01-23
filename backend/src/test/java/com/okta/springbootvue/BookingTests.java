package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.entity.Seat;
import com.okta.springbootvue.entity.Zone;
import com.okta.springbootvue.repository.SeatRepository;
import com.okta.springbootvue.repository.ZoneRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class BookingTests {

    private Validator validator;

    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6001803_testSeatOK() { // ใส่ข้อมูลถูกต้องปกติ

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("N");
        seat.setChooseSeat(zone);
        seat = seatRepository.saveAndFlush(seat);

        Optional<Seat> found = seatRepository.findById(seat.getId());
        assertEquals("C01", found.get().getSeat());
    }

    @Test
    void B6001803_testSeatStatusMustBeNotNull() { // seat status ห้ามเป็นค่าว่าง

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus(null);
        seat.setChooseSeat(zone);

        Set<ConstraintViolation<Seat>> result = validator.validate(seat);

        assertEquals(1, result.size());

        ConstraintViolation<Seat> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("seat_status", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testSeatStatusMustBeYorN() { //seat status ต้องเป็น Y หรือ N เท่านั้น
        
        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);

        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("A");
        seat.setChooseSeat(zone);

        Set<ConstraintViolation<Seat>> result = validator.validate(seat);

        assertEquals(1, result.size());

        ConstraintViolation<Seat> v = result.iterator().next();
        assertEquals("must be only Y or N", v.getMessage());
        assertEquals("seat_status", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testPriceMustLessThanOrEqual8000() { // ราคาที่นั่งในแต่ละโซนต้องมีค่าน้อยกว่าหรือเท่ากับ 8000
        
        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(15000);

        Set<ConstraintViolation<Zone>> result = validator.validate(zone);

        assertEquals(1, result.size());

        ConstraintViolation<Zone> v = result.iterator().next();
        assertEquals("must less than or equal 8000", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }
}
