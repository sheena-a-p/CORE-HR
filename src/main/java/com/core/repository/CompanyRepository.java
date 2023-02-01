package com.core.repository;
import com.core.entity.System.Company;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/* Repository interface for Company
 * Author Sheena AP
 */
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    @Override
    Optional<Company> findById(Integer companyId);
}
