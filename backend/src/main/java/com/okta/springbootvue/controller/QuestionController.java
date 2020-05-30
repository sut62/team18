package com.okta.springbootvue.controller;
import com.okta.springbootvue.entity.Question;
import com.okta.springbootvue.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.200:8082")
@RestController
public class QuestionController {
 @Autowired
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/question")
    public Collection<Question> Question() {
        return questionRepository.findAll().stream().collect(Collectors.toList());
    } 
}

