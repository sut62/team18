package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.Sex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SexRepository extends JpaRepository<Sex, Long> {
    Sex findById(long id);


}