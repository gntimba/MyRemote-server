package com.crfstech.MyRemote.persistence.entity.group;

import javax.persistence.JoinColumn;
import java.io.Serializable;

public class groupKey implements Serializable {

    private static final long serialVersionUID = 4274117860808020636L;
    @JoinColumn(name = "user_id", columnDefinition = "uniqueidentifier")
    String userID;
    @JoinColumn(name = "groups_id", columnDefinition = "uniqueidentifier")
    String groupID;

    public groupKey(String userID, String deviceID) {
        this.userID = userID;
        this.groupID = deviceID;
    }

    public groupKey() {
    }
}
