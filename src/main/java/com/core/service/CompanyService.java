package com.core.service;
import com.core.entity.system.Company;

public interface CompanyService {

    public Company createCompany(Company company);

    public Company updateCompany(Company company);

    public Company getById(Integer companuId);

    Integer getCurrentCompanyId();
}
