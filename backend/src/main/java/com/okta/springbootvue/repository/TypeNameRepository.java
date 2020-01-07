package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.TypeName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TypeNameRepository extends JpaRepository<TypeName, Long> {
    TypeName findById(long id);


}