package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Show;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ShowRepository extends JpaRepository<Show, Long> {
    Show findById(long id);
}