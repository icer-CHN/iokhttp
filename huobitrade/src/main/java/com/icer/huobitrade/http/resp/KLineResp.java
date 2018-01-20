package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.KLine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/20.
 */

public class KLineResp extends BaseResp implements Serializable {
    private List<KLine> data;

    public List<KLine> getData() {
        return data;
    }

    public void setData(List<KLine> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "KLineResp{" +
                "data=" + data +
                '}';
    }
}
