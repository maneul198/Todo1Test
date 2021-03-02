package com.test.restservice.controllers;

import com.test.restservice.clients.EmployeeClient;
import com.test.restservice.models.Employee;
import com.test.soap.wsdl.SaveEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@RestController
@Validated
public class Controller {

    @Autowired
    private EmployeeClient employeeClient;

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
    ) throws java.text.ParseException, Exception {

        Employee employee = buildEmployee(
                name,
                surname,
                documentType,
                documentNumber,
                role,
                salary,
                birthDay,
                hireDay
        );

        SaveEmployeeResponse r = employeeClient.saveEmployee(employee);
        if (!r.getStatus().equals("ok")) {
            throw new Exception();
        }
        return employee;
    }

    private Employee buildEmployee(
            String name,
            String surname,
            String documentType,
            String documentNumber,
            String role,
            String salary,
            String birthDay,
            String hireDay
    ) throws java.text.ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setDocumentType(documentType);
        employee.setDocumentNumber(documentNumber);
        employee.setBirthDay(dateFormat.parse(birthDay));
        employee.setHireDay(dateFormat.parse(hireDay));
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setAge(String.valueOf(getAge(birthDay)));
        employee.setHireTime(getHireTime(hireDay));
        return employee;
    }

    private int getAge(String birthDay) {
        LocalDate localDateBirthDay = LocalDate.parse(birthDay, DateTimeFormatter.ISO_LOCAL_DATE);
        Period diff = Period.between(localDateBirthDay, LocalDate.now());
        return diff.getYears();
    }

    private String getHireTime(String hireDay) {
        LocalDate localDateBirthDay = LocalDate.parse(hireDay, DateTimeFormatter.ISO_LOCAL_DATE);
        Period diff = Period.between(localDateBirthDay, LocalDate.now());
        System.out.println(diff.getDays());
        System.out.println(diff.getMonths());
        System.out.println(diff.getYears());
        return String.format("Years: %s - Months: %s - Days: %s", diff.getYears(), diff.getMonths(), diff.getDays());
    }

}
