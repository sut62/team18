package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SEAT")
public class Seat {

    @Id
    @SequenceGenerator(name = "seat_seq", sequenceName = "seat_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_seq")
    @Column(name = "SEAT_ID", unique = true, nullable = true)

    private @NonNull Long id;

    private @NonNull String seat_no;

    private @NonNull String seat_status;

    // *-1 with zone
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zone.class)
    @JoinColumn(name = "ZONE_ID", insertable = true)
    private Zone seatInZone;

    public void setSeatNum(String name) {
        seat_no = name;
    }

    public void setChooseSeat(Zone zone) {
        seatInZone = zone;
    }

    public void setStatus(String string) {
        seat_status = string;
    }
}