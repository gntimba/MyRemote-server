package com.crfstech.MyRemote.DTO;

import com.crfstech.MyRemote.persistence.entity.group.SendMethod;
import lombok.Data;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
@Data
public class MemberDTO implements Serializable {
    private String name;
    private String phone;
    private String email;
    private String whatsappNo;
    @Enumerated(EnumType.STRING)
    private String sendMethod;
}
