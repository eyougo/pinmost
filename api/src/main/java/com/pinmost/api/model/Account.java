package com.pinmost.api.model;

import java.io.Serializable;

/**
 * Created by mei on 23/03/2017.
 */
public class Account extends BaseModel implements Serializable{

    private static final long serialVersionUID = 8065926076365453775L;
    private Integer id;
    private String username;
    private String password;
    private String mobile;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
