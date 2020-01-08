package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="RECEIPTS")
public class Receipts {

    @Id
    @SequenceGenerator(name="receipts_seq",sequenceName="receipts_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="receipts_seq")
    @Column(name = "RECEIPTS_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Payment.class)
    @JoinColumn(name = "PAYMENT_ID", insertable = true)
    private Payment payment;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JoinColumn(name = "BOOKING_ID", insertable = true)
    private Booking booking;


	public void setCreatedBy(Employee employee) {
        this.employee = employee;
	}
	public void setPayment(Payment payment) {
        this.payment = payment;
	}
	public void setBooking(Booking booking) {
        this.booking = booking;
	}

}