package com.okta.springbootvue.repository;

import java.util.List;

import com.okta.springbootvue.entity.Receipts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReceiptsRepository extends JpaRepository<Receipts, Long> {
    List<Receipts> findById(long id);
}