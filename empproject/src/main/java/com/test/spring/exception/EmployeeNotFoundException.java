package com.test.spring.exception;

public class EmployeeNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	EmployeeNotFoundException(){
		super();
	}
	
	public EmployeeNotFoundException(String args){
		super(args);
	}
}
