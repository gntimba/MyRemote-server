package com.crfstech.MyRemote.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class smsDTO implements Serializable {
    String command,id,phone,message;

    public smsDTO(String message) {
        this.message = message;
    }
    public smsDTO(){}
}
