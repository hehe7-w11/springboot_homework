package com.oocl.springboot_exercise.service.Impl;

import com.oocl.springboot_exercise.model.Company;
import com.oocl.springboot_exercise.model.Employee;
import com.oocl.springboot_exercise.repository.CompanyMemoryRepository;
import com.oocl.springboot_exercise.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMemoryRepository companyMemoryRepository;

    @Override
    public List<Company> getCompanies() {
        return companyMemoryRepository.getCompanies();
    }

    @Override
    public Company getCompanyById(Integer id) {
        return companyMemoryRepository.getCompanyById(id);
    }

    @Override
    public List<Employee> getCompanyEmployees(Integer companyId) {
        // Todo: Employee应该添加companyId字段，并在数据库中做连接查询的操作
        Company company = companyMemoryRepository.getCompanyById(companyId);
        if (company != null) {
            return null;
        }
        return new ArrayList<>();
    }

    @Override
    public void createCompany(Company company) {
        companyMemoryRepository.createCompany(company);
    }

    @Override
    public Company updateCompanyName(Integer id, String name) {
        Company company = companyMemoryRepository.getCompanyById(id);
        if (company != null) {
            company.setName(name);
            companyMemoryRepository.updateCompanyName(company);
        }
        return company;
    }

    @Override
    public void deleteCompanyById(Integer id) {
        companyMemoryRepository.deleteCompanyById(id);
    }
}
