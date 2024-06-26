package com.crfstech.MyRemote.persistence.entity.group;

import com.crfstech.MyRemote.persistence.entity.baseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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
