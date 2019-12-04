package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="SHOW")
public class Show {

    @Id
    @SequenceGenerator(name="SHOW_seq",sequenceName="SHOW_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SHOW_seq")
    @Column(name = "SHOW_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Showtype.class)
    @JoinColumn(name = "SHOWTYPE_ID", insertable = true)
    private Showtpye showtype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ratingshow.class)
    @JoinColumn(name = "RATINGSHOW_ID", insertable = true)
    private Ratingshow ratingshow;
}