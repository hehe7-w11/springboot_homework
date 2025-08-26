package com.oocl.springboot_exercise.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final static Map<Integer, Employee> db = new HashMap<>();

    @PostMapping
    public void add(@RequestBody Employee employee){
        employee.setId(db.size() + 1);
        db.put(db.size() +1, employee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") int id){
        return db.get(id);
    }




}
