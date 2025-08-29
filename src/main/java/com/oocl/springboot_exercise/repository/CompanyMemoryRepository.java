package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyMemoryRepository implements CompanyRepository{

    private final static Map<Integer, Company> db = new HashMap<>();

    public List<Company> getCompanies() {
        return new ArrayList<>(db.values());
    }

    public Company getCompanyById(Integer id) {
        return db.get(id);
    }

    public void createCompany(Company company) {
        company.setId(db.size() + 1);
        db.put(db.size() + 1, company);
    }

    public void updateCompanyName(Company company) {
        db.put(company.getId(), company);
    }

    public void deleteCompanyById(Integer id) {
        db.remove(id);
    }
}
