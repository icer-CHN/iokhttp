package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.BatchCancel;

import java.io.Serializable;

public class BatchCancelResp extends BaseResp implements Serializable {
    private BatchCancel data;

    public BatchCancel getData() {
        return data;
    }

    public void setData(BatchCancel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BatchCancelResp{" +
                "data=" + data +
                '}';
    }
}
