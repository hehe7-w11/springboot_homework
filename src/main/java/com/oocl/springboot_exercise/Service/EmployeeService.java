package com.oocl.springboot_exercise.Service;

import com.oocl.springboot_exercise.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    void addEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

    Employee updateEmployeeById(Integer id, Employee employee);

    void deleteById(int id);

    List<Employee> getEmployeeList();

    List<Employee> getEmployeeByGender(String gender);
}
