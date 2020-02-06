package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotNull
    @Pattern(regexp = "[A-Z]\\d{2}", message = "must match [A-Z]\\d{2}")
    @Column(name = "SEAT_NO")
    private String seat_no;

    @NotNull
    @Pattern(regexp = "[YN]", message = "must be only Y or N")
    @Column(name = "SEAT_STATUS")
    private String seat_status;

    // *-1 with zone
    @NotNull
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

    public Long getId() {
        return this.id;
    }

    public String getSeat() {
        return this.seat_no;
    }
}