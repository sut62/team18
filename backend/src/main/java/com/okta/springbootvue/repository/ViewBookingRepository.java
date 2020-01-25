package com.okta.springbootvue.repository;

import java.util.Collection;

import com.okta.springbootvue.entity.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ViewBookingRepository extends JpaRepository<Booking, Long> {
    Booking findById(long id);

	//find booking by user
    @Query( value = "SELECT * FROM BOOKING b where b.USER_ID = :user ",
            nativeQuery = true)
    Collection<Booking> findBookingByUser(@Param("user") Long user);
}