package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Balance;

import java.io.Serializable;

public class BalanceResp extends BaseResp implements Serializable {
    private Balance data;

    public Balance getData() {
        return data;
    }

    public void setData(Balance data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BalanceResp{" +
                "data=" + data +
                '}';
    }
}
