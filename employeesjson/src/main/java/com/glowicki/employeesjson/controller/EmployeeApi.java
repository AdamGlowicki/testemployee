package com.glowicki.employeesjson.controller;

import com.glowicki.employeesjson.data.EmployeeService;
import com.glowicki.employeesjson.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salary")
@AllArgsConstructor
public class EmployeeApi {

    EmployeeHandler employeeHandler;
    EmployeeService employeeService;

    @GetMapping("/surname/{surname}")
    public String getSalaryByEmployee(@PathVariable String surname) {
        Optional<String> salaryBySurname = employeeHandler.getSalaryBySurname(surname);

        if (salaryBySurname.isPresent()) {
            return salaryBySurname.get();
        }
        throw  new RuntimeException("surname doesn't exist!");
    }

    @GetMapping("/id/{id}")
    public String getSalaryById(@PathVariable int id) {
        Optional<String> salaryBySurname = employeeHandler.getSalaryById(id);

        if (salaryBySurname.isPresent()) {
            return salaryBySurname.get();
        }

        throw  new RuntimeException("id doesn't exist!");
    }

    @GetMapping("/names")
    public List<String> getSalaryById() {
        return employeeHandler.getNames();
    }

    @GetMapping("/all")
    public List<Employee> getAll() {
        return employeeService.getEmployee();
    }
}
