package com.core.serviceImpl;
import com.core.entity.system.Company;
import com.core.repository.CompanyRepository;
import com.core.service.CompanyService;
import com.core.service.CrudService;
import com.core.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends CrudService implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public Company createCompany(Company company) {
        return null;
    }

    @Override
    public Company updateCompany(Company company) {
        return null;
    }

    @Override
    public Company getById(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        return  company;
    }

    @Override
    public Integer getCurrentCompanyId(Integer userId) {
        return userAccountService.getById(userId).getCompanyId();
    }
}
