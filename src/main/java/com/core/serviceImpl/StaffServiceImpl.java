package com.core.serviceImpl;
import com.core.entity.staff.Staff;
import com.core.repository.StaffRepository;
import com.core.service.CompanyService;
import com.core.service.CrudService;
import com.core.service.StaffService;
import com.core.view.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class StaffServiceImpl extends CrudService implements StaffService {

    @Autowired
    private  StaffRepository staffRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public void createStaff(Staff staff) {
        preCreate(staff);
        staffRepository.save(staff);
    }

    @Override
    public void updateStaff(Staff staff) {
        Staff staffDetails = staffRepository.findById(staff.getStaffId()).orElseThrow(() ->{
            throw new NullPointerException("Staff not found !");
        });
        staffDetails = preUpdate(staffDetails);
        staffRepository.save(staffDetails);

    }

    public Staff preCreate(Staff staff) {
        staff.setDateCreated(new Date());
        staff.setDateModified(new Date());
        staff.setStaffCreated(1);
        staff.setStaffModified(1);
        staff.setCompanyId(1);
        return  staff;
    }

    public Staff preUpdate(Staff staff) {
        staff.setDateModified(new Date());
        staff.setStaffModified(1);
        return staff;
    }

    @Override
    public StaffView getStaffView(Integer staffId){
        return  staffRepository.getStaffViewById(staffId);
    }
}
