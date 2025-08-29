package com.oocl.springboot_exercise.service;
import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();

    Company getCompanyById(Integer id);

    void createCompany(Company company);

    Company updateCompanyName(Integer id, String name);

    void deleteCompanyById(Integer id);
}
