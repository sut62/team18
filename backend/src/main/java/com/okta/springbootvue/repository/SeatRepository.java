package com.okta.springbootvue.repository;

import java.util.Collection;

import com.okta.springbootvue.entity.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findById(long id);

    //get seat in each zone
    @Query( value = "SELECT * FROM SEAT s where s.ZONE_ID = :seat and s.SEAT_STATUS = 'N'",
            nativeQuery = true)
    Collection<Seat> findSeatByZone(@Param("seat") Long seat);

    
}