package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.OrderDetail;

import java.io.Serializable;


public class OrderDetailResp extends BaseResp implements Serializable {
    private OrderDetail data;

    public OrderDetail getData() {
        return data;
    }

    public void setData(OrderDetail data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderDetailResp{" +
                "data=" + data +
                '}';
    }
}
