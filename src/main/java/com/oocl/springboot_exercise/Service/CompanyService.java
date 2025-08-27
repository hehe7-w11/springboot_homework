package com.oocl.springboot_exercise.Service;
import com.oocl.springboot_exercise.Model.Company;
import com.oocl.springboot_exercise.Model.Employee;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();

    Company getCompanyById(Integer id);

    List<Employee> getCompanyEmployees(Integer companyId);

    void createCompany(Company company);

    Company updateCompanyName(Integer id, String name);

    void deleteCompanyById(Integer id);
}
