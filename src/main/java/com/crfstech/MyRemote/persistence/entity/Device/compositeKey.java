package com.crfstech.MyRemote.persistence.entity.Device;

import com.crfstech.MyRemote.persistence.entity.User;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

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
