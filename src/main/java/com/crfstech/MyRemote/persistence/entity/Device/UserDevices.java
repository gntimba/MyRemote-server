package com.crfstech.MyRemote.persistence.entity.Device;


import com.crfstech.MyRemote.persistence.entity.User;
import com.crfstech.MyRemote.persistence.entity.baseTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_devices")
@Data
public class UserDevices implements Serializable {
    @JsonIgnore
    @EmbeddedId
    compositeKey id;
    @JsonIgnore
    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "user_id", columnDefinition = "uniqueidentifier")
    private User user;

    @ManyToOne
    @MapsId("deviceID")
    @JoinColumn(name = "devices_id", columnDefinition = "uniqueidentifier")
    private Device device;

    private String phone;
}
