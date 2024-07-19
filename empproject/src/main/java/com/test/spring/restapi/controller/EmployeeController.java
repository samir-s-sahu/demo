package com.test.spring.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.exception.EmployeeNotFoundException;
import com.test.spring.restapi.model.Employee;
import com.test.spring.restapi.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	/**
	 * Getting all Employee list details 
	 * GET - http://localhost:8080/api/employee
	 * @return
	 */
	@GetMapping("/employee")
	 public ResponseEntity<Object> getEmployees() {
	    try {
	    	
	    	return new ResponseEntity<>(employeeService.getEmployee(), HttpStatus.OK);
	    	
	    } catch (EmployeeNotFoundException e) {
	    	return new ResponseEntity<>("Employee list is empty", HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>("Error while getting all employee details", HttpStatus.INTERNAL_SERVER_ERROR);	
	    }
	  }
	
	/**
	 * Getting employee details by employee id 
	 * GET - http://localhost:8080/api/employee/{id}
	 * @param id
	 * @return
	 */
	@GetMapping("/employee/{id}")
	 public ResponseEntity<Object> getEmployees(@PathVariable("id") long id) {
	    try {	
	    	return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
	      
	    } catch (EmployeeNotFoundException e) {
	    	return new ResponseEntity<>("Employee not found", HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>("Error while getting employee details", HttpStatus.INTERNAL_SERVER_ERROR);	
	    }
	  }
	
	/**
	 * Storing the employee details by passing the employee Object
	 * Validate the employee object
	 * POST - http://localhost:8080/api/employee 
	 * @param employee
	 * @return
	 */
	 @RequestMapping(value = "/employee", method = RequestMethod.POST)
	 public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
		 
		 validateEmployeeData(employee);
		 employeeService.saveEmployee(employee);
		 
	     return new ResponseEntity<>("Employee is updated successsfully", HttpStatus.OK);
	   }
	 
	 /**
	  * Getting/calculating the tax details by passing employeeId 
	  * PUT - http://localhost:8080/api/employee/{id}
	  * @param id
	  * @return
	  */
	   @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> getTaxDetails(@PathVariable("id") long id) {
		 
		try {
			return new ResponseEntity<>(employeeService.getTaxDeductionDetails(id), HttpStatus.OK);
			
		} catch (EmployeeNotFoundException e) {
	    	return new ResponseEntity<>("Employee not found", HttpStatus.OK);
	    } catch (Exception e) {
	    	return new ResponseEntity<>("Error while getting employee details", HttpStatus.INTERNAL_SERVER_ERROR);	
	    }
	      
	   }
	   
	   
	   /**
	    * Validating the employee Object
	    * @param employee
	    * @return
	    */
	   private ResponseEntity<Object> validateEmployeeData(Employee employee){
		   
		   if(employee==null){
			   return new ResponseEntity<>("Employee object is null", HttpStatus.BAD_REQUEST);
		   }
		   if(employee.getEmpId()==0) {
				 return new ResponseEntity<>("EmployeeId is empty", HttpStatus.BAD_REQUEST);
			 } else if(employee.getFirstName().isEmpty()) {
				 return new ResponseEntity<>("FirstName is empty", HttpStatus.BAD_REQUEST);
			 } else if(employee.getLastName().isEmpty()) {
				 return new ResponseEntity<>("LastName is empty", HttpStatus.BAD_REQUEST);
			 } else if(employee.getPhoneNumber().isEmpty()) {
				 return new ResponseEntity<>("Phone number is empty", HttpStatus.BAD_REQUEST);
			 } else if(employee.getDoj()==null) {
				 return new ResponseEntity<>("DOJ is empty", HttpStatus.BAD_REQUEST);
			 } else if(employee.getSalary()==0) {
				 return new ResponseEntity<>("Salary is empty", HttpStatus.BAD_REQUEST);
			 }
		   return new ResponseEntity<>("Unknown Error", HttpStatus.BAD_REQUEST);
	}
	   
	
}
