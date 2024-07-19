package com.test.spring.exception;

public class EmployeeValidationException extends Exception{
	
	private static final long serialVersionUID = 1L;

	EmployeeValidationException(){
		super();
	}
	
	public EmployeeValidationException(String args){
		super(args);
	}
}
