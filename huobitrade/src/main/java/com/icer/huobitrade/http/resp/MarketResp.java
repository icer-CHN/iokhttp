package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Market;

import java.io.Serializable;


public class MarketResp extends BaseResp implements Serializable {
    private Market tick;

    public Market getTick() {
        return tick;
    }

    public void setTick(Market tick) {
        this.tick = tick;
    }

    @Override
    public String toString() {
        return "MarketResp{" +
                "tick=" + tick +
                '}';
    }
}
