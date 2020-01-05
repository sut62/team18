
package com.okta.springbootvue.Cancel.entity;

import java.time.LocalDateTime;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="CANCELBOOKING")
public class CancelBooking {
      
    @Id
    @SequenceGenerator(name="CANCELBOOKING_seq",sequenceName="CANCELBOOKING_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CANCELBOOKING_seq")  
    @Column(name = "CANCELBOOKING_ID", unique = true, nullable = true)
    private @NonNull Long ID;
    
    
 @Column(name="DATE")
   private @NonNull LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee cancelBy;


    @OneToOne(fetch = FetchType.EAGER, targetEntity = CancelReason.class)
    @JoinColumn(name = "CANCELREASON_ID", insertable = true)
    private CancelReason cancelCaused;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JoinColumn(name = "BOOKING_ID", insertable = true)
    private Booking cancelBook;
       

	public void setbillby(Employee em) {
      this.cancelBy = em;
	}

	public void setDate(LocalDateTime date) {
         
          this.date = date ;
	}

	public void setCancelCaused(CancelReason cr) {
      this.cancelCaused = cr ;
	}

	public void setBooking(Booking cancelBook) {
           this.cancelBook = cancelBook ;
	}


}





