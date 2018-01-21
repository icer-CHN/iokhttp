package com.icer.huobitrade.http.resp;

import java.io.Serializable;


public class StringResp extends BaseResp implements Serializable {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StringResp{" +
                "data='" + data + '\'' +
                '}';
    }
}
