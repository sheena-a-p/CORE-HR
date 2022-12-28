package com.core.view;
import com.core.entity.staff.Staff;
import lombok.Data;

@Data
public class StaffView extends Staff {
    private  String statusName;
    private  String departmentName;
}
