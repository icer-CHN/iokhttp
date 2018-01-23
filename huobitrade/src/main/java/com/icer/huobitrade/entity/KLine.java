package com.icer.huobitrade.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class KLine implements Serializable {

    /**
     * "id": K线id,
     * "amount": 成交量,
     * "count": 成交笔数,
     * "open": 开盘价,
     * "close": 收盘价,当K线为最晚的一根时，是最新成交价
     * "low": 最低价,
     * "high": 最高价,
     * "vol": 成交额, 即 sum(每一笔成交价 * 该笔的成交量)
     * <p>
     * id : 1499184000
     * amount : 37593.0266
     * count : 0
     * open : 1935.2
     * close : 1879.0
     * low : 1856.0
     * high : 1940.0
     * vol : 7.1031537978665E7
     */
    @PrimaryKey
    private long id;
    private double amount;
    private int count;
    private double open;
    private double close;
    private double low;
    private double high;
    private double vol;
    private String period;
    private String symbol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Result getResult() {
        Result result;
        if (close == open) {
            result = Result.HOLD;
        } else if (close > open) {
            result = Result.UP;
        } else {
            result = Result.DOWN;
        }
        if (close == high) {
            result = Result.HIGH;
        }
        if (close == low) {
            result = Result.LOW;
        }
        if (high == low) {
            result = Result.HOLD;
        }
        return result;
    }

    public String getResultDesc() {
        return getResult().desc;
    }

    public double getChangeValue() {
        return close - open;
    }

    public double getChangeDecimal() {
        double d = getChangeValue() / open;
        String ds = String.format("%.4f", d);
        return Double.valueOf(ds);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", amount=" + amount +
                ", count=" + count +
                ", open=" + open +
                ", close=" + close +
                ", low=" + low +
                ", high=" + high +
                ", vol=" + vol +
                ", symbol=" + symbol +
                '}';
    }

    public enum Result {
        HIGH(2, "最高"),
        UP(1, "上涨"),
        HOLD(0, "持平"),
        DOWN(-1, "下跌"),
        LOW(-2, "最低");

        int val;
        String desc;

        Result(int val, String desc) {
            this.val = val;
            this.desc = desc;
        }
    }
}
