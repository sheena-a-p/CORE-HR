package com.core.hr.service;
import com.core.hr.entity.Staff.Staff;
import com.core.hr.form.StaffForm;
import com.core.hr.view.StaffView;

public interface StaffService {

    void createStaff(StaffForm staffForm);

    void updateStaff(StaffForm staffForm);

    Staff getStaff(Integer staffId);

    StaffView getStaffView(Integer staffId);
}
