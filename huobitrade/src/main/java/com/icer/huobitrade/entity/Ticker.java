package com.icer.huobitrade.entity;

import java.io.Serializable;
import java.util.List;


public class Ticker extends KLine implements Serializable {

    /**
     * "id": K线id,
     * "amount": 成交量,
     * "count": 成交笔数,
     * "open": 开盘价,
     * "close": 收盘价,当K线为最晚的一根时，是最新成交价
     * "low": 最低价,
     * "high": 最高价,
     * "vol": 成交额, 即 sum(每一笔成交价 * 该笔的成交量)
     * "bid": [买1价,买1量],
     * "ask": [卖1价,卖1量]
     * <p>
     * id : 1499225271
     * ts : 1499225271000
     * close : 1885.0
     * open : 1960.0
     * high : 1985.0
     * low : 1856.0
     * amount : 81486.2926
     * count : 42122
     * vol : 1.57052744857082E8
     * ask : [1885,21.8804]
     * bid : [1884,1.6702]
     */

    private long ts;
    private List<Double> ask;
    private List<Double> bid;

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public List<Double> getAsk() {
        return ask;
    }

    public void setAsk(List<Double> ask) {
        this.ask = ask;
    }

    public List<Double> getBid() {
        return bid;
    }

    public void setBid(List<Double> bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "ts=" + ts +
                ", ask=" + ask +
                ", bid=" + bid +
                '}' + super.toString();
    }
}
