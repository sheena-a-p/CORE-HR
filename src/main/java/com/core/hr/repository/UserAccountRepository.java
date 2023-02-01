package com.core.hr.repository;
import com.core.hr.entity.System.UserAccount;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/* Repository interface for User account
 * Author Sheena AP
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> findByEmailAndStatus(String email,Integer status);

    Optional<UserAccount> findByUserId(Integer userId);

}
