package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
