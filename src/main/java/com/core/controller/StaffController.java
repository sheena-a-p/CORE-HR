package com.core.controller;
import com.core.entity.staff.Staff;
import com.core.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired(required = false)
    private StaffService staffService;

    @PostMapping("/save")
    public void saveStaff(@Valid @RequestBody Staff staff){
        try {
            if (staff.getStaffId() == null){
                staffService.createStaff(staff);
            }else {
                staffService.updateStaff(staff);
            }
        }catch (Exception e){
            throw  new RuntimeException("Saving staff details failed !",e);
        }
    }
}
