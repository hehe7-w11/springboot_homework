package com.oocl.springboot_exercise.Controller;

import com.oocl.springboot_exercise.Common.Result;
import com.oocl.springboot_exercise.Model.Employee;
import com.oocl.springboot_exercise.Common.PageResult;
import com.oocl.springboot_exercise.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Result<Employee> getById(@PathVariable("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? Result.success(employee) : Result.fail("查询为空");
    }

    @GetMapping(params = {"gender"})
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
