package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name="Actor")
    private String actor;
    
    @NotNull
    @Column(name="Title")
    private String title;

    @NotNull
    @Column(name="Information")
    private String information;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Ratingshow.class)
    @JoinColumn(name = "RATINGSHOW_ID", insertable = true)
    private Ratingshow ratingshow;

    @NotNull
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

    public void setInformation (String information2) {
        this.information=information2;
    }

	public void setActor(String actor2) {
        this.actor=actor2;
	}
}