package com.core.view;
import com.core.entity.Staff.Staff;
import lombok.Data;

@Data
public class StaffView extends Staff {
    private  String statusName;
    private  String departmentName;
    public StaffView(Staff staff) {
        super(staff);
    }
}
