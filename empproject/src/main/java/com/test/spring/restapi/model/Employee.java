package com.test.spring.restapi.model;

import java.time.LocalDate;
import java.util.List;

public class Employee {

	long empId;
	String firstName;
	String lastName;
	String email;
	List<Long> phoneNumber;
	LocalDate doj;
	double salary;

	public Employee(long empId, String firstName, String lastName, String email, List<Long> phoneNumber,
			LocalDate doj, double salary) {
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.doj = doj;
		this.salary = salary;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Long> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List<Long> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public boolean isNull(Employee emp) {
		if(emp==null) {
			return true;
		}
		return false;
	}

}
