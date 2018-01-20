package com.icer.huobitrade.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class Account implements Serializable {

    /**
     * id : 100009
     * type : spot spot：现货账户
     * state : working working：正常, lock：账户被锁定
     * user-id : 1000
     */

    private int id;
    private String type;
    private String state;
    @SerializedName("user-id")
    private int userId;
    private List<Balance> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Balance> getList() {
        return list;
    }

    public void setList(List<Balance> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", userId=" + userId +
                ", list=" + list +
                '}';
    }

    public static class Balance implements Serializable {

        /**
         * currency : usdt
         * type : trade trade: 交易余额，frozen: 冻结余额
         * balance : 500009195917.4362872650
         */

        private String currency;
        private String type;
        private String balance;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public Double getBalanceInDouble() {
            return Double.parseDouble(balance);
        }

        @Override
        public String toString() {
            return "Balance{" +
                    "currency='" + currency + '\'' +
                    ", type='" + type + '\'' +
                    ", balance='" + balance + '\'' +
                    '}';
        }
    }
}
