package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Account;

import java.io.Serializable;

/**
 * Created by cljlo on 2018/1/21.
 */

public class BalanceResp extends BaseResp implements Serializable {
    private Account data;

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BalanceResp{" +
                "data=" + data +
                '}';
    }
}
