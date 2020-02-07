package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@Entity
@NoArgsConstructor
@Table(name="TIME")
public class Time {
    @Id
    @SequenceGenerator(name="TIME_SEQ",sequenceName="TIME_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TIME_SEQ")
    @Column(name="TIME_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Size(min = 9,max = 12)
    @Column(name = "TIME")
    private String time;

    

	public void setTime(String name) {
        this.time = name;
	}

 
}