package com.crfstech.MyRemote.DTO;

import com.crfstech.MyRemote.persistence.entity.Device.Device;
import com.crfstech.MyRemote.security.CryptoUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceUserDTO implements Serializable {

    private Device device;
    private String phone;
    private String customName;


    public DeviceUserDTO(Device device, String phone, String customName) {
        this.device = device;
        this.phone = CryptoUtil.encrypt(phone);
       this.customName = customName;
    }

    public DeviceUserDTO() {
    }
}
