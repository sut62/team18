package com.booking.backend.entity;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="BOOKING")
public class Booking {

    @Id
    @SequenceGenerator(name="booking_seq",sequenceName="booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="booking_seq")
    @Column(name = "BOOKING_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull int qty;

    private @NonNull int total_price;

    private @NonNull String payment_status;

    @Column(name="BOOKING_TIME")
    private @NonNull LocalDateTime booking_time;
    
    // *-1 with show
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Show.class)
    @JoinColumn(name = "SHOW_ID", insertable = true)
    private Show show;

    // *-1 with showtime
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Showtime.class)
    @JoinColumn(name = "SHOWTIME_ID", insertable = true)
    private Showtime showtime;

    // *-1 with seat
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Seat.class)
    @JoinColumn(name = "SEAT_ID", insertable = true)
    private Seat seat;
        
}