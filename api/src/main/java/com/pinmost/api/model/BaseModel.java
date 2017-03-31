package com.pinmost.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by mei on 23/03/2017.
 */
public abstract class BaseModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedAt;

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
}