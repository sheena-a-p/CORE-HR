package com.core.hr.form;
import lombok.Data;

/* Form for Staff
 * Author Sheena AP
 */
@Data
public class StaffForm {
    private Integer staffId;
    private String staffName;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer department;
    private String dateOfBirth;
}
