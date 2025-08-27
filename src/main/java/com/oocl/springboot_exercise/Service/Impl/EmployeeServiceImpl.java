package com.oocl.springboot_exercise.Service.Impl;

import com.oocl.springboot_exercise.Common.Exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.Model.Employee;
import com.oocl.springboot_exercise.Repository.EmployeeRepository;
import com.oocl.springboot_exercise.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee addEmployee(Employee employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new InvalidEmployeeException("员工年龄应为18-65岁");
        }
        if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new InvalidEmployeeException("员工年龄大于等于30且薪资低于20000，不符合薪资结构");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getById(id);
    }

    @Override
    public Employee updateEmployeeById(Integer id, Employee employee) {
        Employee oldEmployee = employeeRepository.getById(id);
        if (oldEmployee == null) {
            return null;
        }
        if (!oldEmployee.isActive()) {
            throw new InvalidEmployeeException("员工已离职无法更新");
        }
        return employeeRepository.updateEmployee(id, employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = employeeRepository.getById(id);
        employee.setActive(false);
        employeeRepository.updateEmployee(id, employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.getEmployeeList();
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        List<Employee> employeeList = employeeRepository.getEmployeeList();
        if (gender != null) {
            return employeeList.stream()
                    .filter(employee -> gender.equals(employee.getGender()))
                    .collect(Collectors.toList());
        }
        return employeeList;
    }
}
