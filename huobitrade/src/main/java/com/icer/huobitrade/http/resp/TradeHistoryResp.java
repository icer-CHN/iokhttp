package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Trade;

import java.io.Serializable;
import java.util.List;

/**
 * 批量获取最近的交易记录
 * Created by cljlo on 2018/1/20.
 */

public class TradeHistoryResp extends BaseResp implements Serializable {
    private List<Trade> data;

    public List<Trade> getData() {
        return data;
    }

    public void setData(List<Trade> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TradeHistoryResp{" +
                "data=" + data +
                '}';
    }
}
