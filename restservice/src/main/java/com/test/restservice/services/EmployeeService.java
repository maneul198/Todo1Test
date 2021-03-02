package com.test.restservice.services;

import com.test.restservice.models.Employee;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class EmployeeService {

    private static final String DATE_FORMAT = "yyyy-mm-dd";
    public Employee buildEmployee(String name, String surname, String documentType, String documentNumber,
                                   String role, String salary, String birthDay,
                                   String hireDay) throws java.text.ParseException {

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setDocumentType(documentType);
        employee.setDocumentNumber(documentNumber);
        employee.setBirthDay(dateFormat.parse(birthDay));
        employee.setHireDay(dateFormat.parse(hireDay));
        employee.setRole(role);
        employee.setSalary(salary);
        employee.setAge(String.valueOf(getAgeAsString(birthDay)));
        employee.setHireTime(getHireTime(hireDay));
        return employee;
    }

    public int getAge(String birthDay) {
        LocalDate localDateBirthDay = LocalDate.parse(birthDay, DateTimeFormatter.ISO_LOCAL_DATE);
        Period diff = Period.between(localDateBirthDay, LocalDate.now());
        return diff.getYears();
    }

    public String getAgeAsString(String birthDay) {
        LocalDate localDateBirthDay = LocalDate.parse(birthDay, DateTimeFormatter.ISO_LOCAL_DATE);
        Period diff = Period.between(localDateBirthDay, LocalDate.now());
        return String.format("Years: %d - Months: %d - Days: %d", diff.getYears(), diff.getMonths(), diff.getDays());
    }

    public String getHireTime(String hireDay) {
        LocalDate localDateBirthDay = LocalDate.parse(hireDay, DateTimeFormatter.ISO_LOCAL_DATE);
        Period diff = Period.between(localDateBirthDay, LocalDate.now());
        return String.format("Years: %d - Months: %d - Days: %d", diff.getYears(), diff.getMonths(), diff.getDays());
    }
}
