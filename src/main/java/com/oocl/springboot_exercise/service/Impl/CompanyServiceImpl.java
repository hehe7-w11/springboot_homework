package com.oocl.springboot_exercise.service.Impl;

import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.repository.CompanyDBRepository;
import com.oocl.springboot_exercise.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDBRepository companyRepository;

    @Override
    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    @Override
    public Company getCompanyById(Integer id) {
        return companyRepository.getCompanyById(id);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.createCompany(company);
    }

    @Override
    public Company updateCompanyName(Integer id, String name) {
        Company company = companyRepository.getCompanyById(id);
        if (company != null) {
            company.setName(name);
            companyRepository.updateCompanyName(company);
        }
        return company;
    }

    @Override
    public void deleteCompanyById(Integer id) {
        companyRepository.deleteCompanyById(id);
    }
}
