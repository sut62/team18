package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

    @Column(name="TIME_DATE")
    private @NonNull Date timeDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Time.class)
    @JoinColumn(name = "TIME_ID", insertable = true)
    private Time time;

   
}