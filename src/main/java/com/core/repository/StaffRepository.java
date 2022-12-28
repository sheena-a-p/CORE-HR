package com.core.repository;
import com.core.entity.staff.Staff;
import com.core.view.StaffView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Integer> {

    @Query(value = "SELECT * FROM staff_view WHERE staff_id=?1", nativeQuery = true)
    StaffView getStaffViewById(Integer staffId);
}
