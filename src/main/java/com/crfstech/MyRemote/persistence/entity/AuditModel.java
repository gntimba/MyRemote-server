package com.crfstech.MyRemote.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



import java.util.Date;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt", "createdBy", "modifiedBy"},
        allowGetters = true
)
public abstract class AuditModel {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @JsonIgnore
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @LastModifiedBy
    private String modifiedBy;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
