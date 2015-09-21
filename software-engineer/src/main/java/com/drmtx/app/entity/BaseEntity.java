package com.drmtx.app.entity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Contains basic functionality for all Entity subclasses
 * <p>
 * Created by steve on 9/20/15.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "created")
    public Calendar createdDate;

    @Column(name = "updated")
    public Calendar updatedDate;

    @PrePersist
    void createdAt() {
        this.createdDate = this.updatedDate = Calendar.getInstance();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedDate = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }
}
