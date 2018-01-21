package com.icer.huobitrade.http.resp;

import java.io.Serializable;
import java.util.List;

public class CurrencysResp extends BaseResp implements Serializable {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CurrencysResp{" +
                "data=" + data +
                '}';
    }
}
