package com.crfstech.MyRemote.persistence.entity.group;


import com.crfstech.MyRemote.persistence.entity.baseTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "groups")
@Data
@EqualsAndHashCode(callSuper = true)
public class Group extends baseTable implements Serializable {
    private String name;
    private String description;
    @OneToMany(targetEntity = Member.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Member> members;
}

