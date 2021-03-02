package com.test.restservice.controllers;

import com.test.restservice.clients.EmployeeClient;
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
public class Controller {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private EmployeeService employeeService;

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

        Employee employee = employeeService.buildEmployee(name, surname, documentType, documentNumber, role, salary,
                birthDay, hireDay);

        SaveEmployeeResponse r = employeeClient.saveEmployee(employee);
        if (!r.getStatus().equals("ok") || employeeService.getAge(birthDay) < 18) {
            throw new Exception();
        }
        return employee;
    }
}
