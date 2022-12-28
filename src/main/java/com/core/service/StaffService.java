package com.core.service;

import com.core.entity.staff.Staff;
import com.core.view.StaffView;

public interface StaffService {

    public Staff createStaff(Staff staff);

    public Staff updateStaff(Staff staff);

    StaffView getView(Integer staffId);
}
