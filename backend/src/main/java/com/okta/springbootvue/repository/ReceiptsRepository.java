package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Receipts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReceiptsRepository extends JpaRepository<Receipts, Long> {
    Receipts findById(long id);
}