package com.glowicki.employeesjson.data;


import com.glowicki.employeesjson.model.Employee;
import com.glowicki.employeesjson.model.EmployeeWrapper;
import com.google.gson.Gson;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class EmployeeService {

    private static final String PATH = "employees (1).csv";

    public List<Employee> getEmployee() {
        if (PATH.endsWith("json") && getEmployeeFromJson() != null) {
            return getEmployeeFromJson();
        } else if (PATH.endsWith("csv") && getEmployeeFromCsv() != null) {
            return getEmployeeFromCsv();
        }

        return List.of();
    }

    public List<Employee> getEmployeeFromJson() {
        EmployeeWrapper employeeWrapper = null;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(PATH));

            employeeWrapper = gson.fromJson(reader, EmployeeWrapper.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeWrapper.getEmployees();
    }

    @EventListener(ApplicationReadyEvent.class)
    public List<Employee> getEmployeeFromCsv() {
        List<String> employees = null;
        try (Stream<String> stream = Files.lines(Paths.get(PATH))) {
            employees = stream.collect(Collectors.toList());
            employees.remove(0);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapToEmployee(employees);
    }

    private List<Employee> mapToEmployee(List<String> list) {
        String reduce = "[\"][^\"]";
        List<Employee> employees = new ArrayList<>();
        list.forEach(e -> {
            String[] employeeSplit = e.split(";");
            Employee employee = Employee.builder()
                    .id(Integer.valueOf(employeeSplit[0].trim()))
                    .name(employeeSplit[1].replace("\"", "").trim())
                    .surname(employeeSplit[2].replace("\"", "").trim())
                    .job(employeeSplit[3].replace("\"", "").trim())
                    .salary(employeeSplit[4].replace("\"", "").trim())
                    .build();
            employees.add(employee);

        });
        return employees;
    }

}



