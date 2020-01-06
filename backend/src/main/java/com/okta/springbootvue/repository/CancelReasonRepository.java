package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.CancelReason;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CancelReasonRepository extends JpaRepository<CancelReason, Long> {
    CancelReason findById(long id);
    
}