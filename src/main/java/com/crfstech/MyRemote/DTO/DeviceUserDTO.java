package com.crfstech.MyRemote.DTO;

import com.crfstech.MyRemote.persistence.entity.Device.Device;
import com.crfstech.MyRemote.security.CryptoUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceUserDTO implements Serializable {

    Device device;
    String phone;


    public DeviceUserDTO(Device device, String phone) {
        this.device = device;
        this.phone = CryptoUtil.encrypt(phone);
    }

    public DeviceUserDTO() {
    }
}
