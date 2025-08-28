package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {

    private final Map<Integer, Employee> db = new HashMap<>();

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(db.values());
    }

    public Employee save(Employee employee) {
        employee.setId(db.size() + 1);
        db.put(db.size() + 1, employee);
        return db.get(db.size());
    }

    public Employee getById(int id) {
        return db.get(id);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        db.put(id, employee);
        return employee;
    }

    public void deleteEmployeeById(int id) {
        db.remove(id);
    }
}
