package com.okta.springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Collection;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import com.okta.springbootvue.entity.UserRegister;
import com.okta.springbootvue.entity.Sex;
import com.okta.springbootvue.entity.TypeName;
import com.okta.springbootvue.entity.Question;
import com.okta.springbootvue.repository.TypeNameRepository;
import com.okta.springbootvue.repository.SexRepository;
import com.okta.springbootvue.repository.QuestionRepository;
import com.okta.springbootvue.repository.UserRegisterRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class UserRegisterController {
    @Autowired
    private final UserRegisterRepository userregisterRepository;
    @Autowired
    private  TypeNameRepository typenameRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private QuestionRepository questionRepository;

   
    UserRegisterController(UserRegisterRepository userregisterRepository) {
        this.userregisterRepository = userregisterRepository;
    }
    @GetMapping("/checkUser/{email}/{pass}")
    public Collection<UserRegister> getCheckUser(@PathVariable("email") String email, @PathVariable("pass") String pass) {
        return userregisterRepository.checkUser(email, pass);
    }
    


    @GetMapping("/userregister/id={id}")
    public Collection<UserRegister> getuserregister(@PathVariable("id") Long id ) {
        return userregisterRepository.findQuest(id);
    }
    
    @GetMapping("/userregister")
    public Collection<UserRegister> UserRegister() {
        return userregisterRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/userregister/{typename_id}/{name}/{email}/{tel}/{sex_id}/{question_id}/{answer}/{password}")
    public UserRegister newUserRegister(UserRegister newUserRegister,
    @PathVariable long typename_id,
    @PathVariable String name,
    @PathVariable String email,
    @PathVariable String tel,
    @PathVariable long sex_id,
    @PathVariable long question_id,
    @PathVariable String answer,
    @PathVariable String password ){
    
    
    
    Sex sex = sexRepository.findById(sex_id);
    TypeName typename = typenameRepository.findById(typename_id);
    Question question = questionRepository.findById(question_id);
    
    newUserRegister.setSex(sex);
    newUserRegister.setTypeName(typename);
    newUserRegister.setQuestion(question);
    newUserRegister.setName((String)name);
    newUserRegister.setEmail((String)email);
    newUserRegister.setTel((String)tel);
    newUserRegister.setAnswer((String)answer);
    newUserRegister.setPassword((String)password);
    newUserRegister.date(new Date());


    return userregisterRepository.save(newUserRegister);
    
    }
}