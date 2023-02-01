package com.core.service;
import com.core.entity.System.Company;

public interface CompanyService {

    Company createCompany(Company company);

    Company updateCompany(Company company);

    Company getById(Integer companuId);

    Integer getCurrentCompanyId(Integer userId);
}
