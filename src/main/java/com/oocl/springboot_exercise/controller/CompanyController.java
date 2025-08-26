package com.oocl.springboot_exercise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final static Map<Integer, Company> db = new HashMap<>();

    @GetMapping
    public List<Company> getCompanyList(){
        return new ArrayList<>(db.values());
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id){
        return db.get(id);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId){
        Company company = db.get(companyId);
        if(company != null){
            return company.getEmployees();
        }
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company){
        company.setId(db.size() + 1);
        db.put(db.size() + 1, company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyName(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        Company company = db.get(id);
        if (company != null) {
            company.setName(request.get("name"));
            db.put(id, company);
        }
        return company;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Integer id) {
        db.remove(id);
    }
}
