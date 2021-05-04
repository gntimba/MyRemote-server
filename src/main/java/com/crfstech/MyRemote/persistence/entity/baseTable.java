package com.crfstech.MyRemote.persistence.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
@MappedSuperclass
@Audited
public class baseTable extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private boolean active = true;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
