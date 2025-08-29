package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> getEmployeeByGender(String gender);

    List<Employee> findEmployeesByCompany(Company company);
}
