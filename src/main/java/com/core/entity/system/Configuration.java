package com.core.entity.system;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(schema = "system")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer configId;
    private String configName;
    private String configContext;
    private String notes;
    private String value1;
    private String value2;
    private String value3;
}
