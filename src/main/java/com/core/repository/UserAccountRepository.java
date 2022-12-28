package com.core.repository;
import com.core.entity.system.UserAccount;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    Optional<UserAccount> findByEmail(String email);

}
