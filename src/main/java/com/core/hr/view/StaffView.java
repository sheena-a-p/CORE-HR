package com.core.hr.view;
import com.core.hr.entity.Staff.Staff;
import lombok.Data;

@Data
public class StaffView extends Staff {
    private  String statusName;
    private  String departmentName;
    public StaffView(Staff staff) {
        super(staff);
    }
}
