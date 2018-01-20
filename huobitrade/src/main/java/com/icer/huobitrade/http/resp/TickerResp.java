package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Ticker;

import java.io.Serializable;

/**
 * Created by cljlo on 2018/1/20.
 */

public class TickerResp extends BaseResp implements Serializable {
    private Ticker tick;

    public Ticker getTick() {
        return tick;
    }

    public void setTick(Ticker tick) {
        this.tick = tick;
    }

    @Override
    public String toString() {
        return "TickerResp{" +
                "tick=" + tick +
                '}';
    }
}
