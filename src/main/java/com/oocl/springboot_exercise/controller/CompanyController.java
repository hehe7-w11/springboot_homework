package com.oocl.springboot_exercise.controller;

import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.service.CompanyService;
import com.oocl.springboot_exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    private final static Map<Integer, Company> db = new HashMap<>();

    @GetMapping
    public List<Company> getCompanyList() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId) {
        return employeeService.getCompanyEmployees(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) {
        companyService.createCompany(company);
    }

    @PutMapping(value = "/{id}", params = "name")
    public Company updateCompanyName(@PathVariable Integer id, @RequestParam String name) {
        return companyService.updateCompanyName(id, name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompanyById(id);
    }
}
