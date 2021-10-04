package com.crfstech.MyRemote.DTO;

import com.crfstech.MyRemote.persistence.entity.Device.Device;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceUserDTO implements Serializable {

    private Device device;
    private String phone;
    private String customName;


    public DeviceUserDTO(Device device, String phone, String customName) {
        this.device = device;
        this.phone = phone;
        this.customName = customName;
    }

    public DeviceUserDTO() {
    }
}
