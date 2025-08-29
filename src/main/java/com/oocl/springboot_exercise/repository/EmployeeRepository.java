package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee employee);

    Employee getById(Integer id);

    List<Employee> getByGender(String gender);

    List<Employee> getAll();

    Employee update(Employee newEmployee);

    void deleteById(Integer id);

    List<Employee> getByPageSize(Integer pageNumber, Integer pageSize);

}
