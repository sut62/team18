
package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @SequenceGenerator(name="EMPLOYEE_SEQ",sequenceName="EMPLOYEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
    @Column(name="EMPLOYEE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    private  String name;

    @NotNull
    private  String password;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Show> show;

    public void setName(String name) {
		this.name = name;
    }

    public String getName() {
		return this.name;
	}

	public void setPass(String pass) {
    this.password=pass;
	}
}