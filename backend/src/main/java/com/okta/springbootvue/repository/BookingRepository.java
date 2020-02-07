package com.okta.springbootvue.repository;

import java.util.Collection;
import java.util.List;

import com.okta.springbootvue.entity.Booking;
import com.okta.springbootvue.entity.UserRegister;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public
interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findById(long id);
    
    List<Booking> findByChooseUser(UserRegister chooseUser);

    //change booked seat status 
    @Transactional
    @Modifying
    @Query( value = "UPDATE SEAT s SET s.SEAT_STATUS = 'Y' where s.SEAT_ID = :seat",
            nativeQuery = true)
    public void changeSeatStatus(@Param("seat") Long seat);

	//check seat avaliable
    @Query( value = "SELECT * FROM BOOKING b where b.SHOWTIME_ID = :st and b.SEAT_ID = :seat",
            nativeQuery = true)
    Collection<Booking> checkSeat(@Param("st") Long st,@Param("seat") Long seat);
}