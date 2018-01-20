package com.icer.huobitrade.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/20.
 */

public class Ticker implements Serializable {

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

    private int id;
    private long ts;
    private double close;
    private double open;
    private double high;
    private double low;
    private double amount;
    private int count;
    private double vol;
    private List<Double> ask;
    private List<Double> bid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
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
                "id=" + id +
                ", ts=" + ts +
                ", close=" + close +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", amount=" + amount +
                ", count=" + count +
                ", vol=" + vol +
                ", ask=" + ask +
                ", bid=" + bid +
                '}';
    }
}
