package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(name = "BOOKING_TIME")
    private LocalDateTime booking_time;
   
    // *-1 with user
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserRegister.class)
    @JoinColumn(name = "USER_ID", insertable = true)
    private UserRegister chooseUser;

    // *-1 with showtime
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Showtime.class)
    @JoinColumn(name = "SHOWTIME_ID", insertable = true)
    private Showtime chooseShowtime;

    // *-1 with seat
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Seat.class)
    @JoinColumn(name = "SEAT_ID", insertable = true)
    private Seat chooseSeat;

    // *-1 with time
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Time.class)
    @JoinColumn(name = "Time_ID", insertable = true)
    private Time time;

    public void setChooseUser(UserRegister user) {
        chooseUser = user;
    }

    public void setChooseShowtime(Showtime showtime) {
        chooseShowtime = showtime;
    }

    public void setBookingTime(LocalDateTime booking_time) {
        this.booking_time = booking_time;
    }

    public void setChooseSeat(Seat chooseSeat) {
        this.chooseSeat = chooseSeat;
    }

	public void setTime(Time time2) {
        this.time = time2;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getBookingTime() {
        return booking_time;
	}

	public Seat getSeat() {
		return chooseSeat;
	}

	public Showtime getShowtime() {
		return chooseShowtime;
	}

	public UserRegister getUser() {
		return chooseUser;
	}

	public Time getTime() {
		return time;
	}

}