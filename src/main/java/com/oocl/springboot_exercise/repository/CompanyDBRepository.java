package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Company;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyDBRepository implements CompanyRepository {

    @Autowired
    JpaCompanyRepository jpaCompanyRepository;

    @Override
    public void deleteCompanyById(Integer id) {
        jpaCompanyRepository.deleteById(id);
    }

    @Override
    public void updateCompanyName(Company company) {
        jpaCompanyRepository.save(company);
    }

    @Override
    public void createCompany(Company company) {
        jpaCompanyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Integer id) {
        return jpaCompanyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> getCompanies() {
        return jpaCompanyRepository.findAll();
    }
}
