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
        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show = showRepository.saveAndFlush(show);

        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        employee = employeeRepository.saveAndFlush(employee);
        
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setRate("ทุกวัย");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
       
        Showtype showtype = new Showtype();
        showtype.setShname("ตลก");
        showtype = showtypeRepository.saveAndFlush(showtype);
        
        show = showRepository.saveAndFlush(show);

        Optional<Show> found = showRepository.findById(show.getId());
        assertEquals(show, found.get());

    }

    @Test
    void B6015800_testShowtypeMustBeNotNull() { // Showtype ห้ามว่าง
        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show = showRepository.saveAndFlush(show);

        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        employee = employeeRepository.saveAndFlush(employee);
        
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setRate("ทุกวัย");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
       
        Showtype showtype = new Showtype();
        showtype.setShname(null);

        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        ConstraintViolation<Showtype> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("shname", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testEmployeeMustBeNotNull() { // Employee ห้ามว่าง
        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show = showRepository.saveAndFlush(show);

        Employee employee = new Employee();
        employee.setName(null);
        employee.setPass("1234");
        
        
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setRate("ทุกวัย");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
       
        Showtype showtype = new Showtype();
        showtype.setShname("ตลก");
        showtype = showtypeRepository.saveAndFlush(showtype);
       
        Set<ConstraintViolation<Employee>> result = validator.validate(employee);

        assertEquals(1, result.size());

        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testRatingshowMustBeNotNull() { // Ratingshow ห้ามว่าง
        Show show = new Show();
        show.setTitle("Frozen");
        show.setActor("Elsa");
        show.setInformation("ผจญภัยดินแดนหิมะ");
        show = showRepository.saveAndFlush(show);

        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        
        
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setRate(null);
       
        Showtype showtype = new Showtype();
        showtype.setShname("ตลก");
        showtype = showtypeRepository.saveAndFlush(showtype);

        Set<ConstraintViolation<Ratingshow>> result = validator.validate(ratingshow);

        assertEquals(1, result.size());

        ConstraintViolation<Ratingshow> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("rate", v.getPropertyPath().toString());
    }

    @Test
    void B6015800_testShowtypeMustBeGreaterEqual3() {
        Showtype showtype = new Showtype();
        showtype.setShname("12");

        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        assertEquals("size must be between 3 and 9", result.iterator().next().getMessage());
        assertEquals("shname", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void B6015800_testShowtypeMustBeLessEqual9() {
        Showtype showtype = new Showtype();
        showtype.setShname("1234567890");

        Set<ConstraintViolation<Showtype>> result = validator.validate(showtype);

        assertEquals(1, result.size());

        assertEquals("size must be between 3 and 9", result.iterator().next().getMessage());
        assertEquals("shname", result.iterator().next().getPropertyPath().toString());
    }

    @Test
    void B6015800_testTitleNotempty(){
        Show show = new Show();
        show.setTitle("");
        Set<ConstraintViolation<Show>> result = validator.validate(show);
        
        assertEquals(1, result.size());
        ConstraintViolation<Show> v = result.iterator().next();
        assertEquals("must not be empty", v.getMessage());
    }
}