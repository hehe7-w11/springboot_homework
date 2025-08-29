package com.oocl.springboot_exercise.controller.mapper;

import com.oocl.springboot_exercise.controller.dto.EmployeeRequest;
import com.oocl.springboot_exercise.controller.dto.EmployeeResponse;
import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
    }

    public List<EmployeeResponse> toResponse(List<Employee> employees){
        return employees.stream().map(this::toResponse).toList();
    }

    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        employee.setCompany(new Company(employeeRequest.getCompanyId()));
        return employee;
    }
}
