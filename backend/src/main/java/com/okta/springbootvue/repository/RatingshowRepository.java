package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Ratingshow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RatingshowRepository extends JpaRepository<Ratingshow, Long> {
	Ratingshow findById(long id);
}