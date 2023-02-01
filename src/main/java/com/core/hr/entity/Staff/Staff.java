package com.core.hr.entity.Staff;
import com.core.hr.form.StaffForm;
import com.core.hr.entity.System.AuditableModel;
import lombok.Data;
import javax.persistence.*;


/* Entity for Staff
 * Author Sheena AP
 */
@Entity
@Data
@Table(schema = "hr")
public class Staff extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    private String employeeCode;
    private String staffName;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer department;
    private String dateOfBirth;
    private Integer companyId;
    private Integer status;

    public Staff(StaffForm staffForm) {
        this.staffName = staffForm.getStaffName();
        this.email = staffForm.getEmail();
        this.phoneNumber = staffForm.getPhoneNumber();
        this.address = staffForm.getPhoneNumber();
        this.department = staffForm.getDepartment();
        this.dateOfBirth = staffForm.getDateOfBirth();
    }

    public Staff(Staff staff, StaffForm staffForm) {
        this.staffId = staff.getStaffId();
        this.employeeCode = staff.getEmployeeCode();
        this.staffName = staffForm.getStaffName();
        this.email = staffForm.getEmail();
        this.phoneNumber = staffForm.getPhoneNumber();
        this.address = staffForm.getAddress();
        this.department = staffForm.getDepartment();
        this.dateOfBirth = staffForm.getDateOfBirth();
        this.companyId = staff.getCompanyId();
        this.status = staff.getStatus();
        this.setDateCreated(staff.getDateCreated());
        this.setStaffCreated(staff.getStaffCreated());
    }

    public Staff(Staff staff) {
        this.staffId = staff.getStaffId();
        this.employeeCode = staff.getEmployeeCode();
        this.staffName = staff.getStaffName();
        this.email = staff.getEmail();
        this.phoneNumber = staff.getPhoneNumber();
        this.address = staff.getAddress();
        this.department = staff.getDepartment();
        this.dateOfBirth = staff.getDateOfBirth();
        this.companyId = staff.getCompanyId();
        this.status = staff.getStatus();
        this.setDateCreated(staff.getDateCreated());
        this.setStaffCreated(staff.getStaffCreated());
        this.setStaffCreated(staff.getStaffCreated());
        this.setStaffModified(staff.getStaffModified());
    }

    public Staff(){
    }
}
