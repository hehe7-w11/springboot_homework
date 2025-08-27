package com.oocl.springboot_exercise.Repository;

import com.oocl.springboot_exercise.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {

    private final Map<Integer, Employee> db = new HashMap<>(Map.of(
            1, new Employee(1, "John Smith", 32, "male", 5000.0, true),
            2, new Employee(2, "Jane Johnson", 28, "female", 6000.0,true),
            3, new Employee(3, "David Williams", 35, "male", 5500.0,true),
            4, new Employee(4, "Emily Brown", 23, "female", 4500.0,true),
            5, new Employee(5, "Michael Jones", 40, "male", 7000.0,true)));

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(db.values());
    }

    public void save(Employee employee) {
        employee.setId(db.size() + 1);
        db.put(db.size() + 1, employee);
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
