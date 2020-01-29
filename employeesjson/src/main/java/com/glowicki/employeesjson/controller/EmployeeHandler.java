package com.glowicki.employeesjson.controller;

import com.glowicki.employeesjson.data.EmployeeService;
import com.glowicki.employeesjson.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeHandler {

    private EmployeeService employeeService;

    public Optional<String> getSalaryBySurname(String surname) {
        return employeeService.getEmployee().stream()
                .filter(e -> e.getSurname().equals(surname) )
                .map(Employee::getSalary)
                .findFirst();
    }

    public Optional<String> getSalaryById(int id) {
        return employeeService.getEmployee().stream()
                .filter(e -> e.getId() == id)
                .map(Employee::getSalary)
                .findFirst();
    }

    public List<String> getNames() {
        return employeeService.getEmployee().stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
    }
}
