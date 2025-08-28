package com.oocl.springboot_exercise.service.Impl;

import com.oocl.springboot_exercise.common.exception.InvalidEmployeeException;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.repository.EmployeeDBRepository;
import com.oocl.springboot_exercise.repository.EmployeeMemoryRepository;
import com.oocl.springboot_exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDBRepository employeeMemoryRepository;


    @Override
    public Employee addEmployee(Employee employee) {
        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new InvalidEmployeeException("员工年龄应为18-65岁");
        }
        if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new InvalidEmployeeException("员工年龄大于等于30且薪资低于20000，不符合薪资结构");
        }
        return employeeMemoryRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMemoryRepository.getById(id);
    }

    @Override
    public Employee updateEmployeeById(Integer id, Employee employee) {
        Employee oldEmployee = employeeMemoryRepository.getById(id);
        if (oldEmployee == null) {
            return null;
        }
        if (!oldEmployee.isActive()) {
            throw new InvalidEmployeeException("员工已离职无法更新");
        }
        return employeeMemoryRepository.update(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = employeeMemoryRepository.getById(id);
        employee.setActive(false);
        employeeMemoryRepository.update(employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeMemoryRepository.getAll();
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        if (gender == null) {
            return employeeMemoryRepository.getAll();
        }
        return employeeMemoryRepository.getByGender(gender);
    }
}
