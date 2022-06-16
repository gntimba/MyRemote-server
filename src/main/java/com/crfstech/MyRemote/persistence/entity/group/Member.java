package com.crfstech.MyRemote.persistence.entity.group;

import com.crfstech.MyRemote.persistence.entity.baseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "members")
@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends baseTable implements Serializable {
    private String name;
    private String phone;
    private String email;
    private String whatsappNo;
    private String userId;
    @Enumerated(EnumType.STRING)
    private SendMethod sendMethod = SendMethod.SMS;
}
