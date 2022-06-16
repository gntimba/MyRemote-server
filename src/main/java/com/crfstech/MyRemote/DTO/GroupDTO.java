package com.crfstech.MyRemote.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupDTO implements Serializable {

    private String name;
    private String description;
}
