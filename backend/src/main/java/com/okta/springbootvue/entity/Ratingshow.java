package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="RATINGSHOW")
public class Ratingshow {
    @Id
    @SequenceGenerator(name="RATINGSHOW_SEQ",sequenceName="RATINGSHOW_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RATINGSHOW_SEQ")
    @Column(name="RATINGSHOW_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
	  @NotEmpty
    private String rate;

    //@OneToMany(fetch = FetchType.EAGER)

   // private Collection<Show> show;

    public void setName(String name) {
		this.rate = name;
	}
}