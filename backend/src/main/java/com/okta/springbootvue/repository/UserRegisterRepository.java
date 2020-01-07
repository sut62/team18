package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.UserRegister;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface UserRegisterRepository extends JpaRepository<UserRegister, Long> {
    


}