package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Company;

import java.util.List;

public interface CompanyRepository {

    public void deleteCompanyById(Integer id);

    public void updateCompanyName(Company company);

    public void createCompany(Company company);

    public Company getCompanyById(Integer id);

    public List<Company> getCompanies();
}