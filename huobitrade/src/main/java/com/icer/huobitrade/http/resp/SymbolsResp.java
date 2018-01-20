package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Symbol;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class SymbolsResp extends BaseResp implements Serializable {
    private List<Symbol> data;

    public List<Symbol> getData() {
        return data;
    }

    public void setData(List<Symbol> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SymbolsResp{" +
                "data=" + data +
                '}';
    }
}
