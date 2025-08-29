package com.oocl.springboot_exercise.service;

import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;

import java.util.List;


public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

    Employee updateEmployeeById(Integer id, Employee employee);

    void deleteById(int id);

    List<Employee> getEmployeeList();

    List<Employee> getEmployeeByGender(String gender);

    List<Employee> getEmployeesByPage(int page, int size);

    List<Employee> getCompanyEmployees(Integer id);
}
