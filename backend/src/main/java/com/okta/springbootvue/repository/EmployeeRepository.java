package com.okta.springbootvue.repository;

import java.util.Collection;

import com.okta.springbootvue.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findById(long id);

	@Query( value = "SELECT * FROM EMPLOYEE u where u.name = :username and u.password = :pass",
            nativeQuery = true)
    Collection<Employee> checkAdmin(@Param("username") String email,@Param("pass") String pass);
}