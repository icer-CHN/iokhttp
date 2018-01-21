package com.icer.huobitrade.http.resp;

import java.io.Serializable;


public class LongResp extends BaseResp implements Serializable {
    private long data;

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LongResp{" +
                "data=" + data +
                '}';
    }
}
