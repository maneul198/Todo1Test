package com.test.restservice.services;

import com.test.restservice.clients.EmployeeClient;
import com.test.restservice.models.Employee;
import com.test.soap.wsdl.SaveEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeClient employeeClient;

    @Autowired
    EmployeeService(EmployeeClient employeeClient){
        this.employeeClient = employeeClient;
    }

    public SaveEmployeeResponse saveEmployee(Employee employee) {
        SaveEmployeeResponse r = employeeClient.saveEmployee(employee);
        return r;
    }
}
