package com.oocl.springboot_exercise.repository;

import com.oocl.springboot_exercise.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyRepository extends JpaRepository<Company, Integer> {
}
