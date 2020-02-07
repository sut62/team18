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
public class ReceiptsTests {

    private Validator validator;

    @Autowired
    private ReceiptsRepository receiptsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private ShowLocationRepository showLocationRepository;

    @Autowired
    private UserRegisterRepository userregisterRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private TypeNameRepository typenameRepository;
    @Autowired
    private QuestionRepository questionRepository;
   
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private RatingshowRepository ratingshowRepository;
    @Autowired
    private ShowtypeRepository showtypeRepository;
    
    

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    /////////////////////// ใส่ข้อมูลถูกต้องปกติ /////////////////////////////////
    @Test
    void B6015886_testReceiptsOK() throws ParseException { 
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
        String datetime = "2020-02-30";
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
        
        Payment payment = new Payment();
        payment.setName("ชำระเงินสด");
        payment = paymentRepository.saveAndFlush(payment);

        ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
        ZonedDateTime zdt = ZonedDateTime.now( z ) ;
        LocalDateTime receipts_datetime = zdt.toLocalDateTime();
        Receipts receipts = new Receipts();
        receipts.setCreatedBy(employee);
        receipts.setBooking(booking);
        receipts.setPayment(payment);
        receipts.setReceiptsDatetime(receipts_datetime); 

        receipts = receiptsRepository.saveAndFlush(receipts);

        Optional<Receipts> found = receiptsRepository.findById(receipts.getId());
        assertEquals(receipts, found.get());
        assertEquals(employee, found.get().getCreatedBy());
        assertEquals(booking, found.get().getBooking());
        assertEquals(payment, found.get().getPayment());
        assertEquals(receipts_datetime, found.get().getReceiptsDatetime());
    }
    ///////////////////////employeeห้ามเป็น null /////////////////////////////////
    @Test
    void B6015886_testEmployeeMustNotBeNull() throws ParseException { 
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
        show.setEmployee(null);
        show.setRatingshow(ratingshow);
        show.setShowtype(showtype);
        show = showRepository.saveAndFlush(show);

        ShowLocation location = showLocationRepository.findById(1);
        Time time = timeRepository.findById(1);
        Showtime showtime = new Showtime();
        showtime.setTime(time);
        showtime.setShow(show);
        showtime.setLocation(location);
        String datetime = "2020-02-30";
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
        
        Payment payment = new Payment();
        payment.setName("ชำระเงินสด");
        payment = paymentRepository.saveAndFlush(payment);

        ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
        ZonedDateTime zdt = ZonedDateTime.now( z ) ;
        LocalDateTime receipts_datetime = zdt.toLocalDateTime();
        Receipts receipts = new Receipts();
        receipts.setCreatedBy(null);
        receipts.setBooking(booking);
        receipts.setPayment(payment);
        receipts.setReceiptsDatetime(receipts_datetime);  

        Set<ConstraintViolation<Receipts>> result = validator.validate(receipts);

        assertEquals(1, result.size());

        ConstraintViolation<Receipts> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }
    ///////////////////////payment ห้ามเป็น null /////////////////////////////////
    @Test
    void B6015886_testPaymentMustNotBeNull() throws ParseException { 
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
        String datetime = "2020-02-30";
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
        
        Payment payment = new Payment();
        payment.setName("ชำระเงินสด");
        payment = paymentRepository.saveAndFlush(payment);

        ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
        ZonedDateTime zdt = ZonedDateTime.now( z ) ;
        LocalDateTime receipts_datetime = zdt.toLocalDateTime();
        Receipts receipts = new Receipts();
        receipts.setCreatedBy(employee);
        receipts.setBooking(booking);
        receipts.setPayment(null);
        receipts.setReceiptsDatetime(receipts_datetime);  

        Set<ConstraintViolation<Receipts>> result = validator.validate(receipts);

        assertEquals(1, result.size());

        ConstraintViolation<Receipts> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("payment", v.getPropertyPath().toString());
    }
    /////////////////////// bookingห้ามเป็น null /////////////////////////////////
    @Test
    void B6015886_testBookingMustNotBeNull() throws ParseException { // ใส่ข้อมูลถูกต้องปกติ
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
        String datetime = "2020-02-30";
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
        
        Payment payment = new Payment();
        payment.setName("ชำระเงินสด");
        payment = paymentRepository.saveAndFlush(payment);

        ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
        ZonedDateTime zdt = ZonedDateTime.now( z ) ;
        LocalDateTime receipts_datetime = zdt.toLocalDateTime();
        Receipts receipts = new Receipts();
        receipts.setCreatedBy(employee);
        receipts.setBooking(null);
        receipts.setPayment(payment);
        receipts.setReceiptsDatetime(receipts_datetime); 
        Set<ConstraintViolation<Receipts>> result = validator.validate(receipts);

        assertEquals(1, result.size());

        ConstraintViolation<Receipts> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("booking", v.getPropertyPath().toString());
    }

    @Test
    void B6015886_testPaymentTypeMustBeGreaterEqual10() throws ParseException { // ใส่ข้อมูลถูกต้องปกติ
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
        String datetime = "2020-02-30";
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
        
        Payment payment = new Payment();
        payment.setName("123456789");
        //payment = paymentRepository.saveAndFlush(payment);

        ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
        ZonedDateTime zdt = ZonedDateTime.now( z ) ;
        LocalDateTime receipts_datetime = zdt.toLocalDateTime();
        Receipts receipts = new Receipts();
        receipts.setCreatedBy(employee);
        receipts.setBooking(booking);
        receipts.setPayment(payment);
        receipts.setReceiptsDatetime(receipts_datetime); 

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("size must be between 10 and 24", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

    @Test
    void B6015886_testPaymentTypeMustBeLessEqual24() throws ParseException { // ใส่ข้อมูลถูกต้องปกติ
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
        String datetime = "2020-02-30";
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
        
        Payment payment = new Payment();
        payment.setName("1234567890123456789012345");
        //payment = paymentRepository.saveAndFlush(payment);

        ZoneId z = ZoneId.of( "Asia/Bangkok" ) ; 
        ZonedDateTime zdt = ZonedDateTime.now( z ) ;
        LocalDateTime receipts_datetime = zdt.toLocalDateTime();
        Receipts receipts = new Receipts();
        receipts.setCreatedBy(employee);
        receipts.setBooking(booking);
        receipts.setPayment(payment);
        receipts.setReceiptsDatetime(receipts_datetime); 

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("size must be between 10 and 24", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }
}
