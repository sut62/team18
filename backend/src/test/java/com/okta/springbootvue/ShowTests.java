package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.entity.Show;
import com.okta.springbootvue.entity.Showtype;
import com.okta.springbootvue.entity.Employee;
import com.okta.springbootvue.entity.Ratingshow;
import com.okta.springbootvue.repository.ShowRepository;
import com.okta.springbootvue.repository.EmployeeRepository;
import com.okta.springbootvue.repository.RatingshowRepository;
import com.okta.springbootvue.repository.ShowtypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class ShowTests {

    private Validator validator;

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RatingshowRepository ratingshowRepository;
    @Autowired
    private ShowtypeRepository showtypeRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6015800_testInsertDataOK() { // ใส่ข้อมูลถูกต้องปกติ

        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);
        
        show = showRepository.saveAndFlush(show);

        Optional<Show> found = showRepository.findById(show.getId());
        assertEquals(show, found.get());

    }

    @Test
    void B6015800_testActorMustBeNotNull() { 
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor(null);
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);

        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("actor", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testTitleMustBeNotNull() { 
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle(null);
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);

        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("title", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testInformationMustBeNotNull() { 
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation(null);
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);

        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("information", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testEmployeeMustBeNotNull() { // Showtype ห้ามว่าง
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(null);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);

        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testShowTypeMustBeNotNull() { // Showtype ห้ามว่าง
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(null);
        show.setRatingshow(ratingshow);

        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("showtype", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testRatingShowMustBeNotNull() { // Showtype ห้ามว่าง
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(null);

        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ratingshow", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testShowTypeMustBeNotEmpty() { // Employee ห้ามว่าง
        Showtype showtype = new Showtype();
        showtype.setShname("");
    
        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        ConstraintViolation<Showtype> v = result.iterator().next();
        assertEquals("must not be empty", v.getMessage());
        assertEquals("shname", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testRatingShowMustBeNotEmpty() { // Employee ห้ามว่าง
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setRate("");
    
        Set<ConstraintViolation<Ratingshow>> result = validator.validate(ratingshow);

        assertEquals(1, result.size());

        ConstraintViolation<Ratingshow> v = result.iterator().next();
        assertEquals("must not be empty", v.getMessage());
        assertEquals("rate", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testActorMustBeGreaterEqual3() {
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("El");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);
        
        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("size must be between 3 and 50", v.getMessage());
        assertEquals("actor", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testActorMustBeLessThanEqual50() {
        Employee employee = employeeRepository.findById(1);
        
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        
        Showtype showtype = showtypeRepository.findById(1);

        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("zasqwaeadsfdrfgftvhvygjbunmjkmijokl,oklm,jkuiyuhjgjgjgrojgrogj");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show.setEmployee(employee);
        show.setShowtype(showtype);
        show.setRatingshow(ratingshow);
        
        Set<ConstraintViolation<Show>> result = validator.validate(show);

        assertEquals(1, result.size());

        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("size must be between 3 and 50", v.getMessage());
        assertEquals("actor", v.getPropertyPath().toString());
    }

    
}