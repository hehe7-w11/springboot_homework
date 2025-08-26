package com.oocl.springboot_exercise.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final static Map<Integer, Employee> db = new HashMap<>();

    @PostMapping
    public void add(@RequestBody Employee employee) {
        employee.setId(db.size() + 1);
        db.put(db.size() + 1, employee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return db.get(id);
    }

    @GetMapping
    public List<Employee> getEmployeeByGender(@RequestParam(required = false) String gender) {
        List<Employee> employees = new ArrayList<>(db.values());
        if (gender != null) {
            return employees.stream()
                    .filter(employee -> gender.equals(employee.getGender()))
                    .collect(Collectors.toList());
        }
        return employees;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        Employee oldEmployee = db.get(id);
        if(oldEmployee != null){
            oldEmployee.setGender(employee.getGender());
            oldEmployee.setAge(employee.getAge());
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
            db.put(id, oldEmployee);
        }
        return oldEmployee;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        db.remove(id);
    }

    @GetMapping("/page")
    public PageResult<Employee> getEmployeesByPage(
            @RequestParam int page,
            @RequestParam int size) {
        List<Employee> allEmployees = new ArrayList<>(db.values());
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, allEmployees.size());
        List<Employee> pageEmployees = allEmployees.subList(startIndex, endIndex);
        return new PageResult<>(page, size, allEmployees.size(), pageEmployees);
    }

}
