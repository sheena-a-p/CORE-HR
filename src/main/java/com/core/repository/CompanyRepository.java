package com.core.repository;
import com.core.entity.system.Company;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    @Override
    Optional<Company> findById(Integer companyId);
}
