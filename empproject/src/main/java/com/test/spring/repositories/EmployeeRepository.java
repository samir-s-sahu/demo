package com.test.spring.repositories;

import java.util.List;
import java.util.Optional;
import com.test.spring.restapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * We can use H2 db to connect DB
 * Just created interface -  not connecting to DB
 */

/*
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Optional<Employee> getEmployeeDetails(long empId);
    boolean saveEmployeeDetails(Employee employee);
    List<Employee> getEmployee();
    
}
*/
