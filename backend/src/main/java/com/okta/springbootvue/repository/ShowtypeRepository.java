package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.Showtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ShowtypeRepository extends JpaRepository<Showtype, Long> {
	Showtype findById(long id);
}