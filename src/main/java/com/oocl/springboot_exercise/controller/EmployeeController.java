package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.common.Result;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.common.PageResult;
import com.oocl.springboot_exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<Employee> getEmployeeByGender(@RequestParam(name = "gender",required = false) String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/page")
    public PageResult<Employee> getEmployeesByPage(
            @RequestParam int page,
            @RequestParam int size) {
        List<Employee> allEmployees = employeeService.getEmployeeList();
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, allEmployees.size());
        List<Employee> pageEmployees = allEmployees.subList(startIndex, endIndex);
        return new PageResult<>(page, size, allEmployees.size(), pageEmployees);
    }

}
