package com.test.resrservice;

import com.test.resrservice.models.Employee;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Validated
public class Controller {

    @GetMapping("/employee")
    public @ResponseBody
    Employee saveEmployee(
            @RequestParam @NotBlank String name,
            @RequestParam @NotBlank String surname,
            @RequestParam @NotBlank String documentType,
            @RequestParam @NotBlank String documentNumber,
            @RequestParam @NotBlank String role,
            @RequestParam @NotBlank String salary,
            @RequestParam(name = "birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthDay,
            @RequestParam(name = "hireday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hireDay
    ) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setDocumentType(documentType);
        employee.setDocumentNumber(documentNumber);
        employee.setBirthDay(birthDay);
        employee.setHireDay(hireDay);
        employee.setRole(role);
        employee.setSalary(salary);

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateDiference diference = DateDiference.build(employee.getBirthDay(), now);
        System.out.println(formatter.format(now));
        employee.setAgeTime(diference.toString());
        return employee;
    }

}
