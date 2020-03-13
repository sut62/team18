package com.okta.springbootvue;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ShowtimeTests {

    private Validator validator;

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private ShowLocationRepository showLocationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RatingshowRepository ratingshowRepository;
    @Autowired
    private ShowtypeRepository showtypeRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b60001537_testInsertDataOk()throws Exception {//save date ok
        
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setName("18+");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        employee = employeeRepository.saveAndFlush(employee);
        Showtype showtype = new Showtype();
        showtype.setName("ละครเพลง");
        showtype = showtypeRepository.saveAndFlush(showtype);
        Show show = new Show();
        show.setTitle("IU Consert");
        show.setActor("NameActor");
        show.setInformation("title has be test");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);
        ShowLocation location = new ShowLocation();
        location.setLocation("RCA");
        location = showLocationRepository.saveAndFlush(location);
        Time time = new Time();
        time.setTime("10.00-12.00");
        time = timeRepository.saveAndFlush(time);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        
        showtime = showtimeRepository.saveAndFlush(showtime);

        Optional<Showtime> found = showtimeRepository.findById(showtime.getId());
        assertEquals(showtime, found.get());
        }

    @Test
    void b60001537_testDateFuture()throws Exception{ //Date Future
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setName("18+");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        employee = employeeRepository.saveAndFlush(employee);
        Showtype showtype = new Showtype();
        showtype.setName("ละครเพลง");
        showtype = showtypeRepository.saveAndFlush(showtype);
        Show show = new Show();
        show.setTitle("IU Consert");
        show.setActor("NameActor");
        show.setInformation("title has be test");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);
        ShowLocation location = new ShowLocation();
        location.setLocation("RCA");
        location = showLocationRepository.saveAndFlush(location);
        Time time = new Time();
        time.setTime("10.00-12.00");
        time = timeRepository.saveAndFlush(time);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-01-01";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        
        Set<ConstraintViolation<Showtime>> result = validator.validate(showtime);

        assertEquals(1, result.size());

        ConstraintViolation<Showtime> v = result.iterator().next();
        assertEquals("must be a future date", v.getMessage());
        assertEquals("showDate", v.getPropertyPath().toString());

    }

    @Test
    void b60001537_testShowLocationNotNull()throws Exception{//Location notnull
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setName("18+");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        employee = employeeRepository.saveAndFlush(employee);
        Showtype showtype = new Showtype();
        showtype.setName("ละครเพลง");
        showtype = showtypeRepository.saveAndFlush(showtype);
        Show show = new Show();
        show.setTitle("IU Consert");
        show.setActor("NameActor");
        show.setInformation("title has be test");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);
        Time time = new Time();
        time.setTime("20.00-12.00");
        time = timeRepository.saveAndFlush(time);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(null);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        
        Set<ConstraintViolation<Showtime>> result = validator.validate(showtime);

        assertEquals(1, result.size());

        ConstraintViolation<Showtime> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("location", v.getPropertyPath().toString());
        
        //assertEquals("must match \"^([0-1][0-9]|2[0-3]).[0-5][0-9]-([0-1][0-9]|2[0-3]).[0-5][0-9]$\"", v.getMessage());
    }

    @Test
    void b60001537_testShowNotNull()throws Exception{//Location notnull
        
        Time time = new Time();
        time.setTime("20.00-12.00");
        time = timeRepository.saveAndFlush(time);
        ShowLocation location = new ShowLocation();
        location.setLocation("RCA");
        location = showLocationRepository.saveAndFlush(location);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(null);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        
        Set<ConstraintViolation<Showtime>> result = validator.validate(showtime);

        assertEquals(1, result.size());

        ConstraintViolation<Showtime> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("show", v.getPropertyPath().toString());
        
        //assertEquals("must match \"^([0-1][0-9]|2[0-3]).[0-5][0-9]-([0-1][0-9]|2[0-3]).[0-5][0-9]$\"", v.getMessage());
    }

    @Test
    void b60001537_testTimeNotNull()throws Exception{ //Time notnull
        Ratingshow ratingshow = new Ratingshow();
        ratingshow.setName("18+");
        ratingshow = ratingshowRepository.saveAndFlush(ratingshow);
        Employee employee = new Employee();
        employee.setName("peter");
        employee.setPass("1234");
        employee = employeeRepository.saveAndFlush(employee);
        Showtype showtype = new Showtype();
        showtype.setName("ละครเพลง");
        showtype = showtypeRepository.saveAndFlush(showtype);
        Show show = new Show();
        show.setTitle("IU Consert");
        show.setActor("NameActor");
        show.setInformation("title has be test");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);
        ShowLocation location = new ShowLocation();
        location.setLocation("RCA");
        location = showLocationRepository.saveAndFlush(location);
        Showtime showtime = new Showtime();
        showtime.setTime(null);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        
        Set<ConstraintViolation<Showtime>> result = validator.validate(showtime);

        assertEquals(1, result.size());

        ConstraintViolation<Showtime> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("time", v.getPropertyPath().toString());
        
        
    }


    @Test
    void b60001537_testTimeSize(){//timeSize
        Time time = new Time();
        time.setTime("10.00-12.0000");
        Set<ConstraintViolation<Time>> result = validator.validate(time);
        
        assertEquals(1, result.size());
        ConstraintViolation<Time> v = result.iterator().next();
        assertEquals("size must be between 9 and 12", v.getMessage());

    
    }


    @Test
    void b60001537_testShowLocationNotEmpty(){//ShowLocationNotempty
        ShowLocation location = new ShowLocation();
        location.setLocation("");
        Set<ConstraintViolation<ShowLocation>> result = validator.validate(location);
        
        assertEquals(1, result.size());
        ConstraintViolation<ShowLocation> v = result.iterator().next();
        assertEquals("must not be empty", v.getMessage());

    
    }

    }