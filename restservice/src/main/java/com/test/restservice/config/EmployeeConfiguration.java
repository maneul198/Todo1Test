package com.test.restservice.config;

import com.test.restservice.clients.EmployeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class EmployeeConfiguration {

    private static final String DEFAULT_URI = "http://localhost:8081/ws";
    private static final String PATH = "com.test.soap.wsdl";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(PATH);
        return marshaller;
    }

    @Bean
    public EmployeeClient employeeClient(Jaxb2Marshaller marshaller) {
        EmployeeClient client = new EmployeeClient();
        client.setDefaultUri(DEFAULT_URI);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
