package com.test.soapservice.endpoints;

import com.test.soapservice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.test.SaveEmployeeResponse;
import io.test.SaveEmployeeRequest;

@Endpoint
public class Employee {
    private static final String NAMESPACE_URI = "http://test.io/";
    private static final String LOCAL_PART = "saveEmployeeRequest";
    private static final String OK = "ok";

    private EmployeeService employeeService;

    @Autowired
    Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_PART)
    public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request)
            throws java.text.ParseException {
        employeeService.save(request.getEmployee());
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setStatus(OK);
        return response;
    }
}
