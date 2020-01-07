package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Zone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ZoneRepository extends JpaRepository<Zone, Long> {
	Zone findById(long id);

	
    
}