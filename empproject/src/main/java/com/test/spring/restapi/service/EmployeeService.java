package com.test.spring.restapi.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.test.spring.exception.EmployeeNotFoundException;
import com.test.spring.restapi.model.Employee;


public interface EmployeeService {

	ResponseEntity<Object> getEmployee() throws EmployeeNotFoundException;
	Employee getEmployee(long id) throws EmployeeNotFoundException;
	boolean saveEmployee(Employee employee);
	double getTaxDeductionDetails(long id) throws EmployeeNotFoundException;
	
}
