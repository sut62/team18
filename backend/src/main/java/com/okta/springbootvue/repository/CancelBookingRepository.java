package com.okta.springbootvue.repository;



import java.util.Optional;

import com.okta.springbootvue.entity.CancelBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CancelBookingRepository extends JpaRepository<CancelBooking, Long> {
    Optional<CancelBooking> findById(Long id);
        
}