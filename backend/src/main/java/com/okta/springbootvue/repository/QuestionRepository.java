package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);


}