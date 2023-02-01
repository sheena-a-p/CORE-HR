package com.core.service;
import com.core.entity.Staff.Staff;
import com.core.form.StaffForm;
import com.core.view.StaffView;

public interface StaffService {

    void createStaff(StaffForm staffForm);

    void updateStaff(StaffForm staffForm);

    Staff getStaff(Integer staffId);

    StaffView getStaffView(Integer staffId);
}
