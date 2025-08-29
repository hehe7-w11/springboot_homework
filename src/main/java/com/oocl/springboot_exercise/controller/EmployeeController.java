package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.common.Result;
import com.oocl.springboot_exercise.controller.dto.EmployeeResponse;
import com.oocl.springboot_exercise.controller.mapper.EmployeeMapper;
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

    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable("id") int id) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @GetMapping()
    public List<EmployeeResponse> getEmployeeByGender(@RequestParam(name = "gender", required = false) String gender) {
        return employeeMapper.toResponse(employeeService.getEmployeeByGender(gender));
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeMapper.toResponse(employeeService.updateEmployeeById(id, employee));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/page")
    public List<EmployeeResponse> getEmployeesByPage(@RequestParam int page, @RequestParam int size) {
        return employeeMapper.toResponse(employeeService.getEmployeesByPage(page, size));
    }

}
