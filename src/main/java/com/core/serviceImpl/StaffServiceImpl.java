package com.core.serviceImpl;
import com.core.entity.staff.Staff;
import com.core.exception.NotFoundException;
import com.core.repository.StaffRepository;
import com.core.service.CompanyService;
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

    @Override
    public Staff getStaff(Integer staffId) {
        return staffRepository.findById(staffId).orElseThrow(() ->new NotFoundException("Staff not found !"));
    }

    public Staff preCreate(Staff staff) {
        staff.setDateCreated(new Date());
        staff.setDateModified(new Date());
        staff.setStaffCreated(SecurityUtil.getCurrentStaffId());
        staff.setStaffModified(SecurityUtil.getCurrentStaffId());
        staff.setStatus(120);
        staff.setCompanyId(SecurityUtil.getCurrentCompanyId());
        return  staff;
    }

    public Staff preUpdate(Staff staff) {
        staff.setDateModified(new Date());
        staff.setStaffModified(SecurityUtil.getCurrentStaffId());
        return staff;
    }

    @Override
    public StaffView getStaffView(Integer staffId){
        return  staffRepository.getStaffViewById(staffId);
    }
}
