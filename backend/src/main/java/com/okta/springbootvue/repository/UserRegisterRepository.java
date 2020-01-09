package com.okta.springbootvue.repository;

import java.util.Collection;

import com.okta.springbootvue.entity.UserRegister;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface UserRegisterRepository extends JpaRepository<UserRegister, Long> {
    
    UserRegister findById(long id);


    @Query( value = "SELECT * FROM USERREGISTER  where register_id = :id",
            nativeQuery = true)
    Collection<UserRegister> findQuest(@Param("id") Long id);


    @Query( value = "SELECT * FROM USERREGISTER u where u.EMAIL = :email and u.Password = :pass",
            nativeQuery = true)
    Collection<UserRegister> checkUser(@Param("email") String email,@Param("pass") String pass);

}