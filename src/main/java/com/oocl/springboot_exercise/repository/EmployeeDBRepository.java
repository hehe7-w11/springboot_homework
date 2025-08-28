package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDBRepository implements EmployeeRepository{

    @Autowired
    private JpaEmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        repository.save(employee);
        return employee;
    }

    @Override
    public Employee getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getByGender(String gender) {
        return repository.getEmployeeByGender(gender);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee update(Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Employee> getByPageSize(Integer pageNumber, Integer pageSize) {
        return null;
    }
}
