package com.test.restservice.controllers;

import Util.Util;
import com.test.restservice.models.Employee;
import com.test.restservice.services.EmployeeService;
import com.test.soap.wsdl.SaveEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class EmployeeController {

    private static final String OK = "ok";
    private static final int AGE = 18; //21

    private EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public @ResponseBody
    Employee saveEmployee(
            @RequestParam @NotBlank String name,
            @RequestParam @NotBlank String surname,
            @RequestParam @NotBlank String documentType,
            @RequestParam @NotBlank String documentNumber,
            @RequestParam @NotBlank String role,
            @RequestParam @NotBlank String salary,
            @RequestParam(name = "birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String birthDay,
            @RequestParam(name = "hireday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String hireDay
    ) throws Exception {

        Employee employee = Util.buildEmployee(name, surname, documentType, documentNumber, role,
                salary, birthDay, hireDay);

        SaveEmployeeResponse r = employeeService.saveEmployee(employee);

        if (!r.getStatus().equals(OK) || Util.getAge(birthDay) < AGE) {
            throw new Exception();
        }

        return employee;
    }
}
