package com.crfstech.MyRemote.persistence.entity.Device;


import com.crfstech.MyRemote.persistence.entity.User;
import com.crfstech.MyRemote.persistence.entity.baseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_devices")
@Data
public class UserDevices implements Serializable {
    @EmbeddedId
    compositeKey id;
    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("deviceID")
    @JoinColumn(name = "devices_id")
    private Device device;

    private String phone;
    private String customName;
}
