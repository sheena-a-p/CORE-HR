package com.core.hr.serviceImpl;
import com.core.hr.repository.StaffRepository;
import com.core.hr.entity.Staff.Staff;
import com.core.hr.entity.Staff.StaffStatusEnum;
import com.core.hr.exception.NotFoundException;
import com.core.hr.exception.StaffNotFoundException;
import com.core.hr.form.StaffForm;
import com.core.hr.service.CrudService;
import com.core.hr.service.StaffService;
import com.core.hr.util.SecurityUtil;
import com.core.hr.view.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class StaffServiceImpl extends CrudService implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public void createStaff(StaffForm staffForm) {
        Staff staff = preCreate(new Staff(staffForm));
        try {
            staffRepository.save(staff);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateStaff(StaffForm staffForm) {
        Staff staff = staffRepository.findById(staffForm.getStaffId()).orElseThrow(() ->{
            throw new StaffNotFoundException("Staff not found !");
        });
        staff.setStaffName(staffForm.getStaffName());
        staff.setEmail(staffForm.getEmail());
        staff.setPhoneNumber(staffForm.getPhoneNumber());
        staff.setAddress(staffForm.getAddress());
        staff.setDepartment(staffForm.getDepartment());
        staff.setDateOfBirth(staffForm.getDateOfBirth());
        staff = preUpdate(new Staff(staff,staffForm));
        staffRepository.save(staff);
    }

    @Override
    public Staff getStaff(Integer staffId) {
        Staff staff = staffRepository.findByStaffId(staffId).orElseThrow(() ->new NotFoundException("Staff not found !"));
        return staff;
    }

    public Staff preCreate(Staff staff) {
        validateDuplicateEmailId(staff);
        staff.setDateCreated(new Date());
        staff.setDateModified(new Date());
        staff.setStaffCreated(SecurityUtil.getCurrentStaffId());
        staff.setStaffModified(SecurityUtil.getCurrentStaffId());
        staff.setStatus(StaffStatusEnum.ACTIVE.getValue());
        staff.setCompanyId(SecurityUtil.getCurrentCompanyId());
        return  staff;
    }

    public Staff preUpdate(Staff staff) {
        validateDuplicateEmailId(staff);
        staff.setDateModified(new Date());
        staff.setStaffModified(SecurityUtil.getCurrentStaffId());
        return staff;
    }

    @Override
    public StaffView getStaffView(Integer staffId){
        Staff staff = getStaff(staffId);
        return new StaffView(staff);
    }

    public void validateDuplicateEmailId(Staff staff){
        Integer duplicateEmailCount = 0;
        if(staff.getStaffId() != null){
            duplicateEmailCount = staffRepository.countByStaffIdNotAndEmailAndCompanyId(staff.getStaffId(),staff.getEmail(),SecurityUtil.getCurrentCompanyId());
        }else if(staff.getStaffId() == null){
            duplicateEmailCount = staffRepository.countByEmailAndCompanyId(staff.getEmail(),SecurityUtil.getCurrentStaffId());
        }
        if(duplicateEmailCount != 0){
            throw new IllegalArgumentException("Saving staff failed !, Email id already existing with another staff");
        }
    }
}
