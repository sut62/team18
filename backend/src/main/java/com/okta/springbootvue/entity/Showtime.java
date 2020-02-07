package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Data
@Entity
@NoArgsConstructor
@Table(name="Showtime")
public class Showtime {

    @Id
    @SequenceGenerator(name="showtime_seq",sequenceName="showtime_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="showtime_seq")
    @Column(name = "SHOWTIME_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
    //@Pattern(regexp = "(?:[0-9][0-9])?[0-9][0-9]-(1[0-2]|0[0-9])-([0-2][0-9]|3[0-1])")
    @NotNull
    @Future
    @JsonFormat(pattern="yyyy-MM-dd")    
    @Column(name="SHOW_DATE")
    private  Date showDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Time.class)
    @JoinColumn(name = "TIME_ID", insertable = true)
    private Time time;
   
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ShowLocation.class)
    @JoinColumn(name = "SHOWLOCATION_ID", insertable = true)
    private ShowLocation location;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Show.class)
    @JoinColumn(name = "SHOW_ID", insertable = true)
    private Show show;

	public void setShow(Show show2) {
        this.show = show2;
	}

	public void setTime(Time time2) {
        this.time = time2;
	}

	public void setLocation(ShowLocation location2) {
        this.location = location2;
	}

	public void setShowDate(Date date) {
        this.showDate = date;
	}

	public Long getId() {
		return this.id;
	}

}