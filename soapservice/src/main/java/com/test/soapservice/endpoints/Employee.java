package com.test.soapservice.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class Employee {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public SaveEmployeeResponse getCountry(@RequestPayload SaveEmployeeRequest request) {
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        return response;
    }

}
