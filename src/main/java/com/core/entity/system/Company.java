package com.core.entity.system;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(schema = "org")
public class Company extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String companyCode;
    private String companyName;
    private Integer status;
}
