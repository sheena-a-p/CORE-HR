package com.core.entity.staff;
import com.core.entity.system.AuditableModel;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Staff  extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    private Integer employeeCode;
    private String staffName;
    private String emilId;
    private String phoneNumber;
    private String address;
    private Integer department;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private Integer companyId;
    private Integer status;
}
