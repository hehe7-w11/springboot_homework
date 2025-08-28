package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeMemoryRepository implements EmployeeRepository{

    private final Map<Integer, Employee> db = new HashMap<>();

    public List<Employee> getAll() {
        return new ArrayList<>(db.values());
    }

    public Employee save(Employee employee) {
        employee.setId(db.size() + 1);
        db.put(db.size() + 1, employee);
        return db.get(db.size());
    }

    public Employee getById(Integer id) {
        return db.get(id);
    }

    @Override
    public List<Employee> getByGender(String gender) {
        return null;
    }

    public Employee update(Employee employee) {
        db.put(employee.getId(), employee);
        return employee;
    }

    public void deleteById(Integer id) {
        db.remove(id);
    }

    @Override
    public List<Employee> getByPageSize(Integer pageNumber, Integer pageSize) {
        return List.of();
    }
}
