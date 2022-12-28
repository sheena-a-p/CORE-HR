package com.core.entity.system;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(schema = "system")
public class UserAccount extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer userId;
    private  String userName;
    private String email;
    private  String password;
    private  String passwordToken;
    private  String verificationToken;
    private Integer companyId;
    private Integer status;
}
