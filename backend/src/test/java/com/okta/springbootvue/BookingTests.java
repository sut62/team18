package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class BookingTests {

    private Validator validator;

    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private UserRegisterRepository userregisterRepository;
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private TypeNameRepository typenameRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RatingshowRepository ratingshowRepository;
    @Autowired
    private ShowtypeRepository showtypeRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowLocationRepository showLocationRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6001803_testBookingOK() throws ParseException { // ใส่ข้อมูลถูกต้องปกติ
        Sex sex = sexRepository.findById(1);
        Question question = questionRepository.findById(1);
        TypeName typename = typenameRepository.findById(1);
        UserRegister userregister = new UserRegister();
        userregister.setSex(sex);
        userregister.setQuestion(question);
        userregister.setTypeName(typename);
        userregister.setName("Kittichai Jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        userregister = userregisterRepository.saveAndFlush(userregister);

        Employee employee = employeeRepository.findById(1);
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        Showtype showtype = showtypeRepository.findById(1);
        Show show = new Show();
        show.setTitle("IU Concert");
        show.setActor("Lee Ji eun");
        show.setInformation("just example show");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);

        ShowLocation location = showLocationRepository.findById(1);
        Time time = timeRepository.findById(1);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        showtime = showtimeRepository.saveAndFlush(showtime);

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("N");
        seat.setChooseSeat(zone);
        seat = seatRepository.saveAndFlush(seat);

        ZonedDateTime utcZoned = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        ZoneId swissZone = ZoneId.of("Asia/Bangkok");
        ZonedDateTime swissZoned = utcZoned.withZoneSameInstant(swissZone);
        LocalDateTime booking_time = swissZoned.toLocalDateTime();

        Booking booking = new Booking();
        booking.setBookingTime(booking_time);
        booking.setChooseSeat(seat);
        booking.setChooseShowtime(showtime);
        booking.setChooseUser(userregister);
        booking.setTime(time);
        booking = bookingRepository.saveAndFlush(booking);

        Optional<Booking> found = bookingRepository.findById(booking.getId());
        assertEquals(booking, found.get());
        assertEquals(booking_time, found.get().getBookingTime());
        assertEquals(seat, found.get().getSeat());
        assertEquals(showtime, found.get().getShowtime());
        assertEquals(userregister, found.get().getUser());
        assertEquals(time, found.get().getTime());

    }

    @Test
    void B6001803_testZoneNameMustNotBeNull() { // ชื่อ zone ต้องไม่เป็นค่าว่าง
        Zone zone = new Zone();
        zone.setName(null);
        zone.setPrice(1500);

        Set<ConstraintViolation<Zone>> result = validator.validate(zone);

        assertEquals(1, result.size());

        ConstraintViolation<Zone> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("zone", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testZoneNameMustBe1Character() { // zone ต้องมี 1 ตัวอักษรเท่านั้น

        Zone zone = new Zone();
        zone.setName("AL");
        zone.setPrice(4500);

        Set<ConstraintViolation<Zone>> result = validator.validate(zone);

        assertEquals(1, result.size());

        ConstraintViolation<Zone> v = result.iterator().next();
        assertEquals("must be 1 character", v.getMessage());
        assertEquals("zone", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testPriceMustGreaterThanOrEqual100() { // ราคาที่นั่งในแต่ละโซนต้องมีค่ามากกว่าหรือเท่ากับ 100

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(99);

        Set<ConstraintViolation<Zone>> result = validator.validate(zone);

        assertEquals(1, result.size());

        ConstraintViolation<Zone> v = result.iterator().next();
        assertEquals("must greater than or equal 100", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
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
    
    @Test
    void B6001803_testSeatNumMustNotBeNull() { // seat number ต้องไม่เป็นค่าว่าง
        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum(null);
        seat.setStatus("N");
        seat.setChooseSeat(zone);

        Set<ConstraintViolation<Seat>> result = validator.validate(seat);

        assertEquals(1, result.size());

        ConstraintViolation<Seat> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("seat_no", v.getPropertyPath().toString());
    }
    
    @Test
    void B6001803_testSeatNumMustMatchPattern() { // seat number ต้องมี 1 ตัวอักษรและตัวเลข 2 ตัวเท่านั้น
        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("AL007");
        seat.setStatus("N");
        seat.setChooseSeat(zone);

        Set<ConstraintViolation<Seat>> result = validator.validate(seat);

        assertEquals(1, result.size());

        ConstraintViolation<Seat> v = result.iterator().next();
        assertEquals("must match [A-Z]\\d{2}", v.getMessage());
        assertEquals("seat_no", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testSeatStatusMustNotBeNull() { // seat status ต้องไม่เป็นค่าว่าง
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
    void B6001803_testSeatStatusMustBeYorN() { // seat status ต้องเป็น Y หรือ N เท่านั้น

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
    void B6001803_testSeatZoneMustNotBeNull() { // seatInZone ต้องไม่เป็นค่าว่าง
        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("N");
        seat.setChooseSeat(null);

        Set<ConstraintViolation<Seat>> result = validator.validate(seat);

        assertEquals(1, result.size());

        ConstraintViolation<Seat> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("seatInZone", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testUserMustNotBeNull() throws ParseException { // user ต้องไม่เป็นค่าว่าง
        Employee employee = employeeRepository.findById(1);
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        Showtype showtype = showtypeRepository.findById(1);
        Show show = new Show();
        show.setTitle("IU Concert");
        show.setActor("Lee Ji eun");
        show.setInformation("just example show");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);

        ShowLocation location = showLocationRepository.findById(1);
        Time time = timeRepository.findById(1);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        showtime = showtimeRepository.saveAndFlush(showtime);

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("N");
        seat.setChooseSeat(zone);
        seat = seatRepository.saveAndFlush(seat);

        ZonedDateTime utcZoned = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        ZoneId swissZone = ZoneId.of("Asia/Bangkok");
        ZonedDateTime swissZoned = utcZoned.withZoneSameInstant(swissZone);
        LocalDateTime booking_time = swissZoned.toLocalDateTime();

        Booking booking = new Booking();
        booking.setBookingTime(booking_time);
        booking.setChooseSeat(seat);
        booking.setChooseShowtime(showtime);
        booking.setChooseUser(null);
        booking.setTime(time);

        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("chooseUser", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testShowtimeMustNotBeNull() { // showtime ต้องไม่เป็นค่าว่าง
        Sex sex = sexRepository.findById(1);
        Question question = questionRepository.findById(1);
        TypeName typename = typenameRepository.findById(1);
        UserRegister userregister = new UserRegister();
        userregister.setSex(sex);
        userregister.setQuestion(question);
        userregister.setTypeName(typename);
        userregister.setName("Kittichai Jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        userregister = userregisterRepository.saveAndFlush(userregister);

        Employee employee = employeeRepository.findById(1);
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        Showtype showtype = showtypeRepository.findById(1);
        Show show = new Show();
        show.setTitle("IU Concert");
        show.setActor("Lee Ji eun");
        show.setInformation("just example show");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);

        Time time = timeRepository.findById(1);

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("N");
        seat.setChooseSeat(zone);
        seat = seatRepository.saveAndFlush(seat);

        ZonedDateTime utcZoned = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        ZoneId swissZone = ZoneId.of("Asia/Bangkok");
        ZonedDateTime swissZoned = utcZoned.withZoneSameInstant(swissZone);
        LocalDateTime booking_time = swissZoned.toLocalDateTime();

        Booking booking = new Booking();
        booking.setBookingTime(booking_time);
        booking.setChooseSeat(seat);
        booking.setChooseShowtime(null);
        booking.setChooseUser(userregister);
        booking.setTime(time);

        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("chooseShowtime", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testTimeMustNotBeNull() throws ParseException{ // time ต้องไม่เป็นค่าว่าง
        Sex sex = sexRepository.findById(1);
        Question question = questionRepository.findById(1);
        TypeName typename = typenameRepository.findById(1);
        UserRegister userregister = new UserRegister();
        userregister.setSex(sex);
        userregister.setQuestion(question);
        userregister.setTypeName(typename);
        userregister.setName("Kittichai Jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        userregister = userregisterRepository.saveAndFlush(userregister);

        Employee employee = employeeRepository.findById(1);
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        Showtype showtype = showtypeRepository.findById(1);
        Show show = new Show();
        show.setTitle("IU Concert");
        show.setActor("Lee Ji eun");
        show.setInformation("just example show");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);

        ShowLocation location = showLocationRepository.findById(1);
        Time time = timeRepository.findById(1);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        showtime = showtimeRepository.saveAndFlush(showtime);

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);
        Seat seat = new Seat();
        seat.setSeatNum("C01");
        seat.setStatus("N");
        seat.setChooseSeat(zone);
        seat = seatRepository.saveAndFlush(seat);

        ZonedDateTime utcZoned = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        ZoneId swissZone = ZoneId.of("Asia/Bangkok");
        ZonedDateTime swissZoned = utcZoned.withZoneSameInstant(swissZone);
        LocalDateTime booking_time = swissZoned.toLocalDateTime();

        Booking booking = new Booking();
        booking.setBookingTime(booking_time);
        booking.setChooseSeat(seat);
        booking.setChooseShowtime(showtime);
        booking.setChooseUser(userregister);
        booking.setTime(null);

        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("time", v.getPropertyPath().toString());
    }

    @Test
    void B6001803_testSeatMustNotBeNull() throws ParseException{ // seat ต้องไม่เป็นค่าว่าง
        Sex sex = sexRepository.findById(1);
        Question question = questionRepository.findById(1);
        TypeName typename = typenameRepository.findById(1);
        UserRegister userregister = new UserRegister();
        userregister.setSex(sex);
        userregister.setQuestion(question);
        userregister.setTypeName(typename);
        userregister.setName("Kittichai Jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        userregister = userregisterRepository.saveAndFlush(userregister);

        Employee employee = employeeRepository.findById(1);
        Ratingshow ratingshow = ratingshowRepository.findById(1);
        Showtype showtype = showtypeRepository.findById(1);
        Show show = new Show();
        show.setTitle("IU Concert");
        show.setActor("Lee Ji eun");
        show.setInformation("just example show");
        show.setEmployee(employee);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);

        ShowLocation location = showLocationRepository.findById(1);
        Time time = timeRepository.findById(1);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-10-30";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateT = df.parse(datetime);
        showtime.setShowDate(dateT);
        showtime = showtimeRepository.saveAndFlush(showtime);

        Zone zone = new Zone();
        zone.setName("C");
        zone.setPrice(1500);
        zone = zoneRepository.saveAndFlush(zone);

        ZonedDateTime utcZoned = ZonedDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        ZoneId swissZone = ZoneId.of("Asia/Bangkok");
        ZonedDateTime swissZoned = utcZoned.withZoneSameInstant(swissZone);
        LocalDateTime booking_time = swissZoned.toLocalDateTime();

        Booking booking = new Booking();
        booking.setBookingTime(booking_time);
        booking.setChooseSeat(null);
        booking.setChooseShowtime(showtime);
        booking.setChooseUser(userregister);
        booking.setTime(time);

        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("chooseSeat", v.getPropertyPath().toString());
    }

}
