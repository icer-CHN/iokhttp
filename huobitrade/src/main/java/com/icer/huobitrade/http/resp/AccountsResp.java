package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Account;

import java.io.Serializable;
import java.util.List;

public class AccountsResp extends BaseResp implements Serializable {
    private List<Account> data;

    public List<Account> getData() {
        return data;
    }

    public void setData(List<Account> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AccountsResp{" +
                "data=" + data +
                '}';
    }
}
