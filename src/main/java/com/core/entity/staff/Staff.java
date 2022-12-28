package com.core.entity.staff;
import com.core.entity.system.AuditableModel;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(schema = "hr")
public class Staff  extends AuditableModel {
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
}
