package com.test.soapservice.repositories;

import com.test.soapservice.endpoints.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
