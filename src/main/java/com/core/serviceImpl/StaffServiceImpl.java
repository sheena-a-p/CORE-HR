package com.core.serviceImpl;
import com.core.entity.staff.Staff;
import com.core.repository.StaffRepository;
import com.core.service.CrudService;
import com.core.service.StaffService;
import com.core.util.SecurityUtil;
import com.core.view.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class StaffServiceImpl extends CrudService implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public Staff createStaff(Staff staff) {
        preCreate(staff);
       return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return  preUpdate(staff);
    }

    public Staff preCreate(Staff staff) {
        staff.setDateCreated(new Date());
        staff.setStaffCreated(SecurityUtil.getCurrentUserId());
        return  staff;
    }

    public Staff preUpdate(Staff staff) {
        staff.setDateUpdated(new Date());
        staff.setStaffUpdated(SecurityUtil.getCurrentUserId());
        return staff;
    }

    @Override
    public StaffView getView(Integer staffId){
        return  staffRepository.getStaffViewById(staffId);
    }
}
