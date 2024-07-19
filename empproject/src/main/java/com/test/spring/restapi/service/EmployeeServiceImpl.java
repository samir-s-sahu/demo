package com.test.spring.restapi.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import com.test.spring.exception.EmployeeNotFoundException;
//import com.test.spring.repositories.EmployeeRepository;
import com.test.spring.restapi.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	//@Autowired
   //private EmployeeRepository repository;	
   private static Map<Long, Employee> employeeRepo = new HashMap<>();
   
   static {
	  // Using hash table as repository to update and fetch data in behave of database
	   Employee employee1 = new Employee(23452,"Samir Kumar","Sahu","samir30@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-05-20"),51300.50);
	   Employee employee2 = new Employee(23453,"Ram Kumar","Kar","ram@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-02-05"),101300.50);
	   Employee employee3 = new Employee(23454,"Hari Parasad","Panda","hari@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-01-25"),44300.50);
	   Employee employee4 = new Employee(23455,"Abhishek Kumar","Padhi","abhishek@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-05-01"),152300.50);
	   Employee employee5 = new Employee(23456,"Niraj","Kar","niraj@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-03-21"),182300.50);
	   Employee employee6 = new Employee(23457,"Rakesh mohon","Muni","rakesh@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-04-20"),67300.50);
	   Employee employee7 = new Employee(23458,"Rajesh","Das","rajesh@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-02-22"),32300.50);
	   Employee employee8 = new Employee(23459,"Pritam Kumar","Das","pritam@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-03-15"),84300.50);
	   Employee employee9 = new Employee(23460,"Sai Prasad ","Padhy","sai.p@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-01-02"),95300.50);
	   Employee employee10 = new Employee(23461,"Jyothi Prasad","Sahu","jyothi.p@xyz.com",Arrays.asList(3333355555L,7829012345L),LocalDate.parse("2024-06-27"),116300.50);
	   
	   employeeRepo.put(employee1.getEmpId(),employee1);
	   employeeRepo.put(employee2.getEmpId(),employee2);
	   employeeRepo.put(employee3.getEmpId(),employee3);
	   employeeRepo.put(employee4.getEmpId(),employee4);
	   employeeRepo.put(employee5.getEmpId(),employee5);
	   employeeRepo.put(employee6.getEmpId(),employee6);
	   employeeRepo.put(employee7.getEmpId(),employee7);
	   employeeRepo.put(employee8.getEmpId(),employee8);
	   employeeRepo.put(employee9.getEmpId(),employee9);
	   employeeRepo.put(employee10.getEmpId(),employee10);
	 
   }
   
   @RequestMapping(value = "/employee")
   public ResponseEntity<Object> getEmployee() throws EmployeeNotFoundException{
	  if(employeeRepo.isEmpty()) {
		  throw new EmployeeNotFoundException("Employee not found");
	  }
      return new ResponseEntity<>(employeeRepo.values(), HttpStatus.OK);
   }
   
   @Override
   public Employee getEmployee(long id) throws EmployeeNotFoundException {
	   //Employee emp = repository.getEmployeeDetails(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found: "));
	   
	   Employee emp=employeeRepo.get(id);
	   if(emp==null) {
		   throw new EmployeeNotFoundException("Employee not found");
	   }
	   return emp;
    
   }
   
   @Override
   public boolean saveEmployee(Employee employee) {
	   //We can use JPA/Hibernate to connect into DB
	   //return repository.saveEmployeeDetails(employee);
	   
	    employeeRepo.put(employee.getEmpId(),employee);
	    return true;
	   
   }
   
   @Override
   public double getTaxDeductionDetails(long empId) throws EmployeeNotFoundException {
	   /*
       Consider the DOJ while calculating total salary(If the employee joined on May 16th, 
       we should not include April month salary and May month's 15 days salary in total salary)
       */
	   
	   Employee emp=employeeRepo.get(empId);
	   if(emp==null) {
		   throw new EmployeeNotFoundException("Employee not found");
	   }
       double salaryPerMonth = emp.getSalary();
       double totalSalary = salaryPerMonth*12;
       double lossPerDay = salaryPerMonth/30;
       LocalDate dateOfJoining = emp.getDoj(); // 02 APRIL 2024
       Period p = Period.between(dateOfJoining, LocalDate.now().withYear(dateOfJoining.getYear()).withMonth(4).withDayOfMonth(1));
 
       int lossDays = Math.min(0, p.getDays());
       
       System.out.println(lossDays);
       
       totalSalary += lossPerDay*p.getDays();
       
       System.out.println(totalSalary);
       
       double sal = totalSalary, tax = 0.0;
       
        if(sal<=250000) {
            tax = 0;
        } else if (sal>250000 && sal<=500000) {
            tax = (sal-250000) * 0.05;
        } else if (sal >500000 && sal<=1000000) {
            tax = 0 + 250000*0.05 + (sal-500000)*0.10;
        } else if (sal> 1000000) {
            tax = 0 + 250000*0.05 + 500000 * 0.10 + (sal-1000000)*0.2;
       }
       
        if(sal>2500000) {
            tax += (sal-2500000)*0.02;
        }
       
        System.out.println(tax);
       
	   return tax;
   }
   
}
