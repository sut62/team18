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
public class CancelBookingTests {

    private Validator validator;

    @Autowired
    private CancelBookingRepository cancelBookingRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private TypeNameRepository typenameRepository;
    @Autowired
    private QuestionRepository questionRepository;
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
    private EmployeeRepository employeeRepository;
    @Autowired
    private RatingshowRepository ratingshowRepository;
    @Autowired
    private ShowtypeRepository showtypeRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowLocationRepository showLocationRepository;
    @Autowired
    private CancelReasonRepository cancelReasonRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6004408_testMustBeOK() throws ParseException {
        //set ค่า ให้ entity ย่อย
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        UserRegister userregister = new UserRegister();
        userregister.setName("Kittichai Jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);
        userregister = userregisterRepository.saveAndFlush(userregister);
//******************************************************************************
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
        String datetime = "2030-02-15";
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
//******************************************************************************

        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // set คำตอบที่ถูก
        cancelbook.setAns("Chanthaburi");
        cancelbook.setDate(now);
        //set booking
        cancelbook.setCancelBook(booking);
        //set user
        cancelbook.setCancelBy(userregister);
        //set reason
        CancelReason cancelreason = cancelReasonRepository.findById(1);
        cancelbook.setCancelCaused(cancelreason);
        // บันทึกค่า
        cancelbook = cancelBookingRepository.saveAndFlush(cancelbook);
        // เทียบค่าที่บันทึก กับค่าที่ส่งไป
        Optional<CancelBooking> found = cancelBookingRepository.findById(cancelbook.getId());
        assertEquals(cancelbook, found.get());
    }

    @Test

    void B6004408_testMustBeNull() throws ParseException {
                //set ค่า ให้ entity ย่อย
                Sex sex = new Sex();
                Question question = new Question();
                TypeName typename = new TypeName();
                UserRegister userregister = new UserRegister();
                userregister.setName("Kittichai Jitjaroen");
                userregister.setTel("0901316436");
                userregister.setEmail("mosmos11289@gmail.com");
                userregister.setAnswer("Chanthaburi");
                userregister.setPassword("Chanthaburi");
                sex.setName("ชาย");
                sex = sexRepository.saveAndFlush(sex);
                typename.setName("นาย");
                typename = typenameRepository.saveAndFlush(typename);
                question.setName("บ้านเกิดคุณอยู่ที่ไหน");
                question = questionRepository.saveAndFlush(question);
                userregister = userregisterRepository.saveAndFlush(userregister);
        //******************************************************************************
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
                String datetime = "2030-02-15";
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
        //******************************************************************************

        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // set null
        cancelbook.setAns(null);
        cancelbook.setDate(now);
        //set booking
        cancelbook.setCancelBook(booking);
        //set user
        cancelbook.setCancelBy(userregister);
        //set reason
        CancelReason cancelreason = cancelReasonRepository.findById(1);
        cancelbook.setCancelCaused(cancelreason);
        // เปรียบเทียบไซส์
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_testMustBeMin() throws ParseException {
                //set ค่า ให้ entity ย่อย
                Sex sex = new Sex();
                Question question = new Question();
                TypeName typename = new TypeName();
                UserRegister userregister = new UserRegister();
                userregister.setName("Kittichai Jitjaroen");
                userregister.setTel("0901316436");
                userregister.setEmail("mosmos11289@gmail.com");
                userregister.setAnswer("Chanthaburi");
                userregister.setPassword("Chanthaburi");
                sex.setName("ชาย");
                sex = sexRepository.saveAndFlush(sex);
                typename.setName("นาย");
                typename = typenameRepository.saveAndFlush(typename);
                question.setName("บ้านเกิดคุณอยู่ที่ไหน");
                question = questionRepository.saveAndFlush(question);
                userregister = userregisterRepository.saveAndFlush(userregister);
        //******************************************************************************
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
                String datetime = "2030-02-15";
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
        //******************************************************************************

        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // set ค่า1ตัว
        cancelbook.setAns("w");
        cancelbook.setDate(now);
        //set booking
        cancelbook.setCancelBook(booking);
        //set user
        cancelbook.setCancelBy(userregister);
        //set reason
        CancelReason cancelreason = cancelReasonRepository.findById(1);
        cancelbook.setCancelCaused(cancelreason);
        // เทียบค่าที่บันทึก
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("ERROR MIN", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test

    void B6004408_testMustBeMax() throws ParseException {
                //set ค่า ให้ entity ย่อย
                Sex sex = new Sex();
                Question question = new Question();
                TypeName typename = new TypeName();
                UserRegister userregister = new UserRegister();
                userregister.setName("Kittichai Jitjaroen");
                userregister.setTel("0901316436");
                userregister.setEmail("mosmos11289@gmail.com");
                userregister.setAnswer("Chanthaburi");
                userregister.setPassword("Chanthaburi");
                sex.setName("ชาย");
                sex = sexRepository.saveAndFlush(sex);
                typename.setName("นาย");
                typename = typenameRepository.saveAndFlush(typename);
                question.setName("บ้านเกิดคุณอยู่ที่ไหน");
                question = questionRepository.saveAndFlush(question);
                userregister = userregisterRepository.saveAndFlush(userregister);
        //******************************************************************************
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
                String datetime = "2030-02-15";
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
        //******************************************************************************

        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // setค่ามากที่สุด
        cancelbook.setAns("0123456789012345678901234567890123456789");
        cancelbook.setDate(now);
        //set booking
        cancelbook.setCancelBook(booking);
        //set user
        cancelbook.setCancelBy(userregister);
        //set reason
        CancelReason cancelreason = cancelReasonRepository.findById(1);
        cancelbook.setCancelCaused(cancelreason);
        // เทียบค่าที่บันทึก
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("ERROR MAX", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_testPattern() throws ParseException {
                //set ค่า ให้ entity ย่อย
                Sex sex = new Sex();
                Question question = new Question();
                TypeName typename = new TypeName();
                UserRegister userregister = new UserRegister();
                userregister.setName("Kittichai Jitjaroen");
                userregister.setTel("0901316436");
                userregister.setEmail("mosmos11289@gmail.com");
                userregister.setAnswer("Chanthaburi");
                userregister.setPassword("Chanthaburi");
                sex.setName("ชาย");
                sex = sexRepository.saveAndFlush(sex);
                typename.setName("นาย");
                typename = typenameRepository.saveAndFlush(typename);
                question.setName("บ้านเกิดคุณอยู่ที่ไหน");
                question = questionRepository.saveAndFlush(question);
                userregister = userregisterRepository.saveAndFlush(userregister);
        //******************************************************************************
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
                String datetime = "2030-02-15";
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
        //******************************************************************************

        // สร้าง object now
        LocalDateTime now = LocalDateTime.now();
        // สร้าง object cancelbook
        CancelBooking cancelbook = new CancelBooking();
        // setค่า
        cancelbook.setAns("asdasdกขคงะา ิ ี ุ ู");
        cancelbook.setDate(now);
        //set booking
        cancelbook.setCancelBook(booking);
        //set user
        cancelbook.setCancelBy(userregister);
        //set reason
        CancelReason cancelreason = cancelReasonRepository.findById(1);
        cancelbook.setCancelCaused(cancelreason);
        // เทียบค่า
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelbook);
        assertEquals(1, result.size());

        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("Wrong Pattern", v.getMessage());
        assertEquals("Ans", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_DateintehPastorPresent() throws ParseException {
                //set ค่า ให้ entity ย่อย
                Sex sex = new Sex();
                Question question = new Question();
                TypeName typename = new TypeName();
                UserRegister userregister = new UserRegister();
                userregister.setName("Kittichai Jitjaroen");
                userregister.setTel("0901316436");
                userregister.setEmail("mosmos11289@gmail.com");
                userregister.setAnswer("Chanthaburi");
                userregister.setPassword("Chanthaburi");
                sex.setName("ชาย");
                sex = sexRepository.saveAndFlush(sex);
                typename.setName("นาย");
                typename = typenameRepository.saveAndFlush(typename);
                question.setName("บ้านเกิดคุณอยู่ที่ไหน");
                question = questionRepository.saveAndFlush(question);
                userregister = userregisterRepository.saveAndFlush(userregister);
        //******************************************************************************
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
                String datetime = "2030-02-15";
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
        //******************************************************************************

        // สร้าง object cancelBook
        CancelBooking cancelBook = new CancelBooking();
        // ใส่ค่าที่เรากำหนดไว้
        LocalDateTime lDate = LocalDateTime.parse("2030-11-03T12:45:30");
        // ใส่ค่าที่เรากำหนดไว้
        cancelBook.setDate(lDate);
        cancelBook.setAns("abcdef");
        //set booking
        cancelBook.setCancelBook(booking);
        //set user
        cancelBook.setCancelBy(userregister);
        //set reason
        CancelReason cancelreason = cancelReasonRepository.findById(1);
        cancelBook.setCancelCaused(cancelreason);
        // ตรวจสอบ error และเก็บค่า error ในรูปแบบ set
        Set<ConstraintViolation<CancelBooking>> result = validator.validate(cancelBook);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<CancelBooking> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }

    @Test
    void B6004408_testCancelReasonNotBeNull(){
        CancelReason cr = new CancelReason();
        cr.setReason(null);
        Set<ConstraintViolation<CancelReason>> result = validator.validate(cr);
        
        assertEquals(1, result.size());
        ConstraintViolation<CancelReason> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());

    
    }


}
