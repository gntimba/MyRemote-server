
package com.crfstech.MyRemote.persistence.entity.Device;

import com.crfstech.MyRemote.model.TYPE;
import com.crfstech.MyRemote.persistence.entity.baseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
@EqualsAndHashCode(callSuper = true)
public class Device extends baseTable implements Serializable {
    private String model;

    private String description;

    private TYPE type;
    @OneToMany(targetEntity = Button.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Button> commands;

}
