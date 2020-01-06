package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="SHOW")
public class Show {
    @Id
    @SequenceGenerator(name="SHOW_SEQ",sequenceName="SHOW_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SHOW_SEQ")
    @Column(name="SHOW_ID",unique = true, nullable = true)
    private @NonNull Long id;
    
    @Column(name="Title")
    private @NonNull String title;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ratingshow.class)
    @JoinColumn(name = "RATINGSHOW_ID", insertable = true)
    private Ratingshow ratingshow;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Showtype.class)
    @JoinColumn(name = "SHOWTYPE_ID", insertable = true)
    private Showtype showtype;

    public void setEmployee(Employee employee2) {
        this.employee=employee2;
    }

    public void setRatingshow(Ratingshow ratingshow2) {
        this.ratingshow=ratingshow2;
    }

    public void setShowtype(Showtype showtype2) {
        this.showtype=showtype2;
    }

    public void setTitle(String title2) {
        this.title=title2;
    }
}