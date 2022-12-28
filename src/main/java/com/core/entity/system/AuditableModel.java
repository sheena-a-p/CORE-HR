package com.core.entity.system;
import lombok.Data;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class AuditableModel {

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    private  Integer staffCreated;
    private  Integer staffUpdated;
}
