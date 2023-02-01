package com.core.repository;
import com.core.entity.Staff.Staff;
import com.core.view.StaffView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/* Repository interface for Staff
 * Author Sheena AP
 */
public interface StaffRepository extends CrudRepository<Staff, Integer> {

    Optional<Staff> findByStaffId(Integer staffId);

    Integer countByEmailAndCompanyId(String email,Integer companyId);
    Integer countByStaffIdNotAndEmailAndCompanyId(Integer staffId,String email,Integer companyId);

    @Query(value = "SELECT * FROM hr.staff_view WHERE staff_id=?1", nativeQuery = true)
    StaffView getStaffViewById(Integer staffId);
}
