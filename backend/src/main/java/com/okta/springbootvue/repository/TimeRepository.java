package com.okta.springbootvue.repository;
import com.okta.springbootvue.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TimeRepository extends JpaRepository<Time, Long> {
	Time findById(long id);
}