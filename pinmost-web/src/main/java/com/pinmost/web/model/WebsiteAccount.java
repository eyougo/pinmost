package com.pinmost.web.model;

import java.io.Serializable;

/**
 * Created by mei on 07/08/2017.
 */
public class WebsiteAccount extends Website implements Serializable{

    private static final long serialVersionUID = 8003346119549615602L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
