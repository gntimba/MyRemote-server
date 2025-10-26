package com.crfstech.MyRemote.persistence.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.Objects;
@MappedSuperclass
@Audited
public class baseTable extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private boolean active = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        baseTable baseTable = (baseTable) o;
        return active == baseTable.active && Objects.equals(id, baseTable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active);
    }
}
