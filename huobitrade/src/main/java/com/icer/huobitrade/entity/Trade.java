package com.icer.huobitrade.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/20.
 */

public class Trade implements Serializable {

    /**
     * id : 600848670
     * ts : 1489464451000
     * data : [{"id":600848670,"price":7962.62,"amount":0.0122,"direction":"buy","ts":1489464451000}]
     */

    private int id;
    private long ts;
    private List<TradeData> data;

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

    public List<TradeData> getData() {
        return data;
    }

    public void setData(List<TradeData> data) {
        this.data = data;
    }

    public static class TradeData implements Serializable {
        /**
         * id : 600848670
         * price : 7962.62
         * amount : 0.0122
         * direction : buy
         * ts : 1489464451000
         */

        private long id;
        private double price;
        private double amount;
        private String direction;
        private long ts;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public long getTs() {
            return ts;
        }

        public void setTs(long ts) {
            this.ts = ts;
        }

        public boolean isBuy() {
            return "buy".equalsIgnoreCase(direction);
        }

        @Override
        public String toString() {
            return "TradeData{" +
                    "id=" + id +
                    ", price=" + price +
                    ", amount=" + amount +
                    ", direction='" + direction + '\'' +
                    ", ts=" + ts +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", ts=" + ts +
                ", data=" + data +
                '}';
    }
}
