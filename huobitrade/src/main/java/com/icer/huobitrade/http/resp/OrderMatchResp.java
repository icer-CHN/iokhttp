package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Match;

import java.io.Serializable;
import java.util.List;


public class OrderMatchResp extends BaseResp implements Serializable {
    private List<Match> data;

    public List<Match> getData() {
        return data;
    }

    public void setData(List<Match> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderMatchResp{" +
                "data=" + data +
                '}';
    }
}
