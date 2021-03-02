package com.test.restservice.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.test.soap.wsdl.SaveEmployeeRequest;
import com.test.soap.wsdl.SaveEmployeeResponse;
import com.test.soap.wsdl.Employee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EmployeeClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(EmployeeClient.class);
    private static final String URI = "http://localhost:8081/ws/employees";
    private static final String SOAP_ACTION = "http://test.io/saveEmployeeRequest";
    private static final String DATE_FORMAT = "yyyy-mm-dd";

    private Employee buildEmployee(com.test.restservice.models.Employee employee) {
        Employee soapEmployee = new Employee();
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        soapEmployee.setName(employee.getName());
        soapEmployee.setSurname(employee.getSurname());
        soapEmployee.setDocumentType(employee.getDocumentType());
        soapEmployee.setDocumentNumber(employee.getDocumentNumber());
        soapEmployee.setBirthDay(dateFormat.format(employee.getBirthDay()));
        soapEmployee.setHireDay(dateFormat.format(employee.getHireDay()));
        soapEmployee.setSalary(employee.getSalary());
        soapEmployee.setRole(employee.getRole());
        soapEmployee.setAge(employee.getAge());
        soapEmployee.setHireTime(employee.getHireTime());
        return soapEmployee;
    }

    public SaveEmployeeResponse saveEmployee(com.test.restservice.models.Employee employee) {
        SaveEmployeeRequest request = new SaveEmployeeRequest();
        request.setEmployee(buildEmployee(employee));
        log.info("Requesting location for " + employee);

        SaveEmployeeResponse response = (SaveEmployeeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URI, request, new SoapActionCallback(SOAP_ACTION));
        return response;
    }
}
