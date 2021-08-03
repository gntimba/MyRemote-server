
package com.crfstech.MyRemote.persistence.entity.Device;

import com.crfstech.MyRemote.persistence.entity.baseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "buttons")
@Data
@EqualsAndHashCode(callSuper = true)
public class Button extends baseTable implements Serializable {

    private String name;
    private String icon;
    private String smsCommands;
}
