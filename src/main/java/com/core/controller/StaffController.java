package com.core.controller;
import com.core.form.StaffForm;
import com.core.service.StaffService;
import com.core.view.StaffView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/* Controller to manage Staff
 * Author Sheena AP
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/save")
    public void saveStaff(@Valid @RequestBody StaffForm staffForm){
        if (staffForm.getStaffId() == null){
            staffService.createStaff(staffForm);
        }else {
            staffService.updateStaff(staffForm);
        }
    }

    @PostMapping("/view")
    public StaffView viewStaff(@RequestParam(value = "staffId",required = true)Integer staffId) {
        return staffService.getStaffView(staffId);
    }
}
