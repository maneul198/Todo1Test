package com.test.soapservice.endpoints;

import com.test.soapservice.models.EmployeeEntity;
import com.test.soapservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.test.SaveEmployeeResponse;
import io.test.SaveEmployeeRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Endpoint
public class Employee {
    private static final String NAMESPACE_URI = "http://test.io/";
    private static final String LOCAL_PART = "saveEmployeeRequest";
    private static final String OK = "ok";
    private static final String DATE_FORMAT = "yyyy-mm-dd";

    private EmployeeRepository employeeRepository;

    @Autowired
    Employee(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_PART)
    @ResponsePayload
    public SaveEmployeeResponse getCountry(@RequestPayload SaveEmployeeRequest request) throws java.text.ParseException{
        employeeRepository.save(buildEntity(request.getEmployee()));
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setStatus(OK);
        return response;
    }

    private EmployeeEntity buildEntity(io.test.Employee employee) throws java.text.ParseException{
        EmployeeEntity employeeEntity = new EmployeeEntity();
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
