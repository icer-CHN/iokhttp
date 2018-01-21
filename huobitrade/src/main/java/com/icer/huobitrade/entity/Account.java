package com.icer.huobitrade.entity;

import java.io.Serializable;

public class Account implements Serializable {


    /**
     * id : 1466855
     * type : spot
     * subtype :
     * state : working
     */

    private long id;
    private String type;
    private String subtype;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isMarginAccount() {
        return "margin".equalsIgnoreCase(type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
