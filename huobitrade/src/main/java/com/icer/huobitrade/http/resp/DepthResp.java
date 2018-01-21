package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Depth;

import java.io.Serializable;

public class DepthResp extends BaseResp implements Serializable {
    private Depth tick;

    public Depth getTick() {
        return tick;
    }

    public void setTick(Depth tick) {
        this.tick = tick;
    }

    @Override
    public String toString() {
        return "DepthResp{" +
                "tick=" + tick +
                '}';
    }
}
