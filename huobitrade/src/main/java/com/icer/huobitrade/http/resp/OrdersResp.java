package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class OrdersResp extends BaseResp implements Serializable {
    private List<Order> data;

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrdersResp{" +
                "data=" + data +
                '}';
    }
}
