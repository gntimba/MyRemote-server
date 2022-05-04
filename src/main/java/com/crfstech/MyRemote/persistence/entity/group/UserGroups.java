package com.crfstech.MyRemote.persistence.entity.group;

import com.crfstech.MyRemote.persistence.entity.Device.Device;
import com.crfstech.MyRemote.persistence.entity.Device.compositeKey;
import com.crfstech.MyRemote.persistence.entity.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users_groups")
@Data
public class UserGroups implements java.io.Serializable {
    @EmbeddedId
    groupKey id;
    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "user_id", columnDefinition = "uniqueidentifier")
    private User user;

    @ManyToOne
    @MapsId("groupID")
    @JoinColumn(name = "group_id", columnDefinition = "uniqueidentifier")
    private Group group;
}
