package com.crfstech.MyRemote.persistence.entity.Device;

import com.crfstech.MyRemote.persistence.entity.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class compositeKey implements Serializable {
    @JoinColumn(name = "user_id",columnDefinition="uniqueidentifier")
     String userID;
    @JoinColumn(name = "devices_id",columnDefinition="uniqueidentifier")
     String deviceID;

    public compositeKey(String userID, String deviceID) {
        this.userID = userID;
        this.deviceID = deviceID;
    }

    public compositeKey() {
    }
}
