
package com.okta.springbootvue.entity;

import java.time.LocalDateTime;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import lombok.*;

import com.okta.springbootvue.entity.Question;
import com.okta.springbootvue.entity.UserRegister;

import org.springframework.lang.Nullable;

import com.okta.springbootvue.entity.Booking;

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
    private @NonNull Long id;
    

  @Size(min = 2, message = "ERROR MIN")
  @Size(max = 30, message = "ERROR MAX")
  @Pattern(regexp = "[A-Za-zก-๙1234567890]*", message = "Wrong Pattern")
    private @NotNull String Ans;
 
  @PastOrPresent
  @Column(name="DATE")
   private @NotNull LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserRegister.class)
    @JoinColumn(name = "REGISTER_ID", insertable = true)
    private UserRegister cancelBy;


    @OneToOne(fetch = FetchType.EAGER, targetEntity = CancelReason.class)
    @JoinColumn(name = "CANCELREASON_ID", insertable = true)
    private CancelReason cancelCaused;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JoinColumn(name = "BOOKING_ID", insertable = true)
    private Booking cancelBook;

    
       

}





