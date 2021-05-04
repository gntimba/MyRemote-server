package com.crfstech.MyRemote.persistence.entity;

import com.crfstech.MyRemote.model.ROLE;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@Data
public class User extends baseTable implements Serializable {

    private String firstName ;
    private String lastName;
    private String picture;
    private Date dob;
    private String phoneNumber;
    private String password;
    private String email;
    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="roles",
            joinColumns = @JoinColumn(name="user_id")
    )
    @Column(name="user_role")
    private Set<ROLE> roles;
}