package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.Employee;
import com.okta.springbootvue.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "172.17.0.201:8082")
@RestController
public class EmployeeController {
    
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping("/checkAdmin/{username}/{pass}")
    public Collection<Employee> getCheckSeat(@PathVariable("username") String username, @PathVariable("pass") String pass) {
        return employeeRepository.checkAdmin(username, pass);
    }

    @GetMapping("/employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> Employees(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

}
