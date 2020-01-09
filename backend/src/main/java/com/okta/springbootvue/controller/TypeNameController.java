package com.okta.springbootvue.controller;
import com.okta.springbootvue.entity.TypeName;
import com.okta.springbootvue.repository.TypeNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class TypeNameController {

   
    @Autowired
    private final TypeNameRepository typeNameRepository;

    public TypeNameController(TypeNameRepository typeNameRepository) {
        this.typeNameRepository = typeNameRepository;
    }

    @GetMapping("/typeName")
    public Collection<TypeName> TypeName() {
        return typeNameRepository.findAll().stream().collect(Collectors.toList());
    }
}
    



