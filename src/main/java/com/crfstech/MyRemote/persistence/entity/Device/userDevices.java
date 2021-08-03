package com.crfstech.MyRemote.persistence.entity.Device;


import com.crfstech.MyRemote.persistence.entity.User;
import com.crfstech.MyRemote.persistence.entity.baseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users_devices")
@Data
public class userDevices extends baseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devices_id")
    private Device device;

    private String phone;
}
