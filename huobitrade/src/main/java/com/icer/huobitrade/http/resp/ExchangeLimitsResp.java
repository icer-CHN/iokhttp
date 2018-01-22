package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.ExchangeLimits;

import java.io.Serializable;


public class ExchangeLimitsResp extends BaseResp implements Serializable {
    private ExchangeLimits data;

    public ExchangeLimits getData() {
        return data;
    }

    public void setData(ExchangeLimits data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ExchangeLimitsResp{" +
                "data=" + data +
                '}';
    }
}
