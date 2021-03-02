package com.test.soapservice.services;

import com.test.soapservice.models.Employee;
import com.test.soapservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class EmployeeService {
    private static final String DATE_FORMAT = "yyyy-mm-dd";

    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void save(io.test.Employee employee) throws java.text.ParseException {
        employeeRepository.save(buildEntity(employee));
    }

    private Employee buildEntity(io.test.Employee employee) throws java.text.ParseException {
        Employee employeeEntity = new Employee();
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        employeeEntity.setName(employee.getName());
        employeeEntity.setSurname(employee.getSurname());
        employeeEntity.setDocumentType(employee.getDocumentType());
        employeeEntity.setDocumentNumber(employee.getDocumentNumber());
        employeeEntity.setBirthDay(dateFormat.parse(employee.getBirthDay()));
        employeeEntity.setHireDay(dateFormat.parse(employee.getHireDay()));
        employeeEntity.setRole(employee.getRole());
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setHireTime(employee.getHireTime());
        employeeEntity.setBirthTime(employee.getAge());
        return employeeEntity;
    }
}
