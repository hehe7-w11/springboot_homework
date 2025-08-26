package com.oocl.springboot_exercise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
