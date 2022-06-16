package com.crfstech.MyRemote.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class AssignGroupDTO implements Serializable {
    String groupId;
    String memberId;
}
