package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.entity.Showtype;
import com.okta.springbootvue.repository.ShowtypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class ShowTests {

    private Validator validator;

    @Autowired
    private ShowtypeRepository showtypeRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6015800_testInsertDataOK() { // ใส่ข้อมูลถูกต้องปกติ

        Showtype showtype = new Showtype();
        showtype.setName("โรแมนติก");
        showtype = showtypeRepository.saveAndFlush(showtype);

        Optional<Showtype> found = showtypeRepository.findById(showtype.getId());
        assertEquals("โรแมนติก", found.get().getShname());
    }

    @Test
    void B6015800_testShowtypeMustBeNotNull() { // Showtype ห้ามว่าง
        Showtype showtype = new Showtype();
        showtype.setShname(null);

        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        ConstraintViolation<Showtype> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("shname", v.getPropertyPath().toString());
    }
    @Test
    void B6015800_testShowtypeMustBeGreaterEqual3() {
        Showtype showtype = new Showtype();
        showtype.setShname("123");

        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        assertEquals("size must be between 3 and 9", result.iterator().next().getMessage());
        assertEquals("shname", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void B6015800_testShowtypeMustBeLessEqual9() {
        Showtype showtype = new Showtype();
        showtype.setShname("123456789");

        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        assertEquals("size must be between 3 and 9", result.iterator().next().getMessage());
        assertEquals("shname", result.iterator().next().getPropertyPath().toString());
    }

}