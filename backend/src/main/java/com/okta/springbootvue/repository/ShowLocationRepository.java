package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.ShowLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ShowLocationRepository extends JpaRepository<ShowLocation, Long> {
	ShowLocation findById(long id);
}