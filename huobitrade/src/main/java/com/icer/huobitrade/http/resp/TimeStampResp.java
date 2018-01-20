package com.icer.huobitrade.http.resp;

import java.io.Serializable;

/**
 * Created by cljlo on 2018/1/21.
 */

public class TimeStampResp extends BaseResp implements Serializable {
    private long data;

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TimeStampResp{" +
                "data=" + data +
                '}';
    }
}
