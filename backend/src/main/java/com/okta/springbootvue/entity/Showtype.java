package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="SHOWTYPE")
public class Showtype {
	@Id
	@SequenceGenerator(name="SHOWTYPE_SEQ",sequenceName="SHOWTYPE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SHOWTYPE_SEQ")
	@Column(name="SHOWTYPE_ID",unique = true, nullable = true)
	private @NonNull Long id;

	private @NonNull String shname;

	//@OneToMany(fetch = FetchType.EAGER)
	// mappedBy  = "rentCustomer"
	//private Collection<Show> show;

	public void setName(String name) {
		this.shname = name;
	}
}
