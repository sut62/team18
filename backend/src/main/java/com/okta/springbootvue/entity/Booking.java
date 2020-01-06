package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@NoArgsConstructor
@Table(name = "BOOKING")
public class Booking {

    @Id
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @Column(name = "BOOKING_ID", unique = true, nullable = true)

    private @NonNull Long id;

    private @NonNull String booking_time;

    // *-1 with user
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "USER_ID", insertable = true)
    private User chooseUser;

    // *-1 with showtime
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Showtime.class)
    @JoinColumn(name = "SHOWTIME_ID", insertable = true)
    private Showtime chooseShowtime;

    // *-1 with seat
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Seat.class)
    @JoinColumn(name = "SEAT_ID", insertable = true)
    private Seat chooseSeat;

    public void setChooseUser(User user) {
        chooseUser = user;
    }

    public void setChooseShowtime(Showtime showtime) {
        chooseShowtime = showtime;
    }

    public void setBookingTime(LocalDateTime myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss น.");
        String formattedDate = myDateObj.format(myFormatObj);
        booking_time = formattedDate;
    }

    public void setChooseSeat(Seat chooseSeat) {
        this.chooseSeat = chooseSeat;
    }

}