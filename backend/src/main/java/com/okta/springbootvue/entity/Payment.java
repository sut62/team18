package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENT")
public class Payment {

    @Id
    @SequenceGenerator(name="payment_seq",sequenceName="payment_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_seq")  
    @Column(name = "PAYMENT_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    @NotEmpty
    private String type;

    //@OneToMany(fetch = FetchType.EAGER)
    //private Collection<Receipts> receipts;

	public void setName(String name) {
        this.type = name;
	}
}
