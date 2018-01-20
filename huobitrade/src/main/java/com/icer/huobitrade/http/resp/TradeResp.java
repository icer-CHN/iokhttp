package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Trade;

import java.io.Serializable;

/**
 * 获取 Trade Detail 数据
 * Created by cljlo on 2018/1/20.
 */

public class TradeResp extends BaseResp implements Serializable {
    private Trade tick;

    public Trade getTick() {
        return tick;
    }

    public void setTick(Trade tick) {
        this.tick = tick;
    }

    @Override
    public String toString() {
        return "TradeResp{" +
                "tick=" + tick +
                '}';
    }
}
