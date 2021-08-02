package com.crfstech.MyRemote.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
@MappedSuperclass
@Audited
public class baseTable extends AuditModel implements Serializable {
    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "APPLICATION_ID" , columnDefinition="uniqueidentifier")
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
