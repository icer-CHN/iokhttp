package com.icer.huobitrade.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class Balance implements Serializable {

    /**
     * id : 1466855
     * type : spot
     * state : working
     * list : [{"currency":"act","type":"trade","balance":"0.000000000000000000"},{"currency":"act","type":"frozen","balance":"0.000000000000000000"},{"currency":"adx","type":"trade","balance":"0.000000000000000000"},{"currency":"adx","type":"frozen","balance":"0.000000000000000000"},{"currency":"aidoc","type":"trade","balance":"0.000000000000000000"},{"currency":"aidoc","type":"frozen","balance":"0.000000000000000000"},{"currency":"appc","type":"trade","balance":"0.000000000000000000"},{"currency":"appc","type":"frozen","balance":"0.000000000000000000"},{"currency":"ast","type":"trade","balance":"0.000000000000000000"},{"currency":"ast","type":"frozen","balance":"0.000000000000000000"},{"currency":"bat","type":"trade","balance":"0.000000000000000000"},{"currency":"bat","type":"frozen","balance":"0.000000000000000000"},{"currency":"bch","type":"trade","balance":"0.000000000000000000"},{"currency":"bch","type":"frozen","balance":"0.000000000000000000"},{"currency":"bcd","type":"trade","balance":"0.000000000000000000"},{"currency":"bcd","type":"frozen","balance":"0.000000000000000000"},{"currency":"bcx","type":"trade","balance":"0.000000000000000000"},{"currency":"bcx","type":"frozen","balance":"0.000000000000000000"},{"currency":"bifi","type":"trade","balance":"0.000000000000000000"},{"currency":"bifi","type":"frozen","balance":"0.000000000000000000"},{"currency":"bt1","type":"trade","balance":"0.000000000000000000"},{"currency":"bt1","type":"frozen","balance":"0.000000000000000000"},{"currency":"bt2","type":"trade","balance":"0.000000000000000000"},{"currency":"bt2","type":"frozen","balance":"0.000000000000000000"},{"currency":"btc","type":"trade","balance":"0.000000000000000000"},{"currency":"btc","type":"frozen","balance":"0.000000000000000000"},{"currency":"btg","type":"trade","balance":"0.000000000000000000"},{"currency":"btg","type":"frozen","balance":"0.000000000000000000"},{"currency":"btm","type":"trade","balance":"0.000000000000000000"},{"currency":"btm","type":"frozen","balance":"0.000000000000000000"},{"currency":"chat","type":"trade","balance":"0.000000000000000000"},{"currency":"chat","type":"frozen","balance":"0.000000000000000000"},{"currency":"cmt","type":"trade","balance":"0.000000000000000000"},{"currency":"cmt","type":"frozen","balance":"0.000000000000000000"},{"currency":"cvc","type":"trade","balance":"0.000000000000000000"},{"currency":"cvc","type":"frozen","balance":"0.000000000000000000"},{"currency":"dash","type":"trade","balance":"0.000000000000000000"},{"currency":"dash","type":"frozen","balance":"0.000000000000000000"},{"currency":"dat","type":"trade","balance":"0.000000000000000000"},{"currency":"dat","type":"frozen","balance":"0.000000000000000000"},{"currency":"dbc","type":"trade","balance":"0.000000000000000000"},{"currency":"dbc","type":"frozen","balance":"0.000000000000000000"},{"currency":"dgd","type":"trade","balance":"0.000000000000000000"},{"currency":"dgd","type":"frozen","balance":"0.000000000000000000"},{"currency":"dta","type":"trade","balance":"0.000000000000000000"},{"currency":"dta","type":"frozen","balance":"0.000000000000000000"},{"currency":"elf","type":"trade","balance":"0.000000000000000000"},{"currency":"elf","type":"frozen","balance":"0.000000000000000000"},{"currency":"eos","type":"trade","balance":"0.000043064938292119"},{"currency":"eos","type":"frozen","balance":"0.000000000000000000"},{"currency":"etc","type":"trade","balance":"0.000000000000000000"},{"currency":"etc","type":"frozen","balance":"0.000000000000000000"},{"currency":"etf","type":"trade","balance":"0.000000000000000000"},{"currency":"etf","type":"frozen","balance":"0.000000000000000000"},{"currency":"eth","type":"trade","balance":"0.000000000000000000"},{"currency":"eth","type":"frozen","balance":"0.000000000000000000"},{"currency":"evx","type":"trade","balance":"0.000000000000000000"},{"currency":"evx","type":"frozen","balance":"0.000000000000000000"},{"currency":"gas","type":"trade","balance":"0.000000000000000000"},{"currency":"gas","type":"frozen","balance":"0.000000000000000000"},{"currency":"gnt","type":"trade","balance":"0.000000000000000000"},{"currency":"gnt","type":"frozen","balance":"0.000000000000000000"},{"currency":"gnx","type":"trade","balance":"0.000000000000000000"},{"currency":"gnx","type":"frozen","balance":"0.000000000000000000"},{"currency":"hsr","type":"trade","balance":"0.000000000000000000"},{"currency":"hsr","type":"frozen","balance":"0.000000000000000000"},{"currency":"icx","type":"trade","balance":"0.000000000000000000"},{"currency":"icx","type":"frozen","balance":"0.000000000000000000"},{"currency":"iost","type":"trade","balance":"0.000000000000000000"},{"currency":"iost","type":"frozen","balance":"0.000000000000000000"},{"currency":"itc","type":"trade","balance":"0.000000000000000000"},{"currency":"itc","type":"frozen","balance":"0.000000000000000000"},{"currency":"knc","type":"trade","balance":"0.000000000000000000"},{"currency":"knc","type":"frozen","balance":"0.000000000000000000"},{"currency":"let","type":"trade","balance":"0.000000000000000000"},{"currency":"let","type":"frozen","balance":"0.000000000000000000"},{"currency":"link","type":"trade","balance":"0.000000000000000000"},{"currency":"link","type":"frozen","balance":"0.000000000000000000"},{"currency":"ltc","type":"trade","balance":"0.000000000000000000"},{"currency":"ltc","type":"frozen","balance":"0.000000000000000000"},{"currency":"mana","type":"trade","balance":"0.000000000000000000"},{"currency":"mana","type":"frozen","balance":"0.000000000000000000"},{"currency":"mco","type":"trade","balance":"0.000000000000000000"},{"currency":"mco","type":"frozen","balance":"0.000000000000000000"},{"currency":"mds","type":"trade","balance":"0.000000000000000000"},{"currency":"mds","type":"frozen","balance":"0.000000000000000000"},{"currency":"mtl","type":"trade","balance":"0.000000000000000000"},{"currency":"mtl","type":"frozen","balance":"0.000000000000000000"},{"currency":"nas","type":"trade","balance":"0.000000000000000000"},{"currency":"nas","type":"frozen","balance":"0.000000000000000000"},{"currency":"neo","type":"trade","balance":"0.000000000000000000"},{"currency":"neo","type":"frozen","balance":"0.000000000000000000"},{"currency":"omg","type":"trade","balance":"0.000000000000000000"},{"currency":"omg","type":"frozen","balance":"0.000000000000000000"},{"currency":"ost","type":"trade","balance":"0.000000000000000000"},{"currency":"ost","type":"frozen","balance":"0.000000000000000000"},{"currency":"pay","type":"trade","balance":"0.000000000000000000"},{"currency":"pay","type":"frozen","balance":"0.000000000000000000"},{"currency":"powr","type":"trade","balance":"0.000000000000000000"},{"currency":"powr","type":"frozen","balance":"0.000000000000000000"},{"currency":"propy","type":"trade","balance":"0.000000000000000000"},{"currency":"propy","type":"frozen","balance":"0.000000000000000000"},{"currency":"qash","type":"trade","balance":"0.000000000000000000"},{"currency":"qash","type":"frozen","balance":"0.000000000000000000"},{"currency":"qsp","type":"trade","balance":"0.000000000000000000"},{"currency":"qsp","type":"frozen","balance":"0.000000000000000000"},{"currency":"qtum","type":"trade","balance":"0.000000000000000000"},{"currency":"qtum","type":"frozen","balance":"0.000000000000000000"},{"currency":"qun","type":"trade","balance":"0.000000000000000000"},{"currency":"qun","type":"frozen","balance":"0.000000000000000000"},{"currency":"rcn","type":"trade","balance":"0.000000000000000000"},{"currency":"rcn","type":"frozen","balance":"0.000000000000000000"},{"currency":"rdn","type":"trade","balance":"0.000000000000000000"},{"currency":"rdn","type":"frozen","balance":"0.000000000000000000"},{"currency":"req","type":"trade","balance":"0.000000000000000000"},{"currency":"req","type":"frozen","balance":"0.000000000000000000"},{"currency":"rpx","type":"trade","balance":"0.000000000000000000"},{"currency":"rpx","type":"frozen","balance":"0.000000000000000000"},{"currency":"salt","type":"trade","balance":"0.000000000000000000"},{"currency":"salt","type":"frozen","balance":"0.000000000000000000"},{"currency":"sbtc","type":"trade","balance":"0.000000000000000000"},{"currency":"sbtc","type":"frozen","balance":"0.000000000000000000"},{"currency":"smt","type":"trade","balance":"0.000000000000000000"},{"currency":"smt","type":"frozen","balance":"0.000000000000000000"},{"currency":"snt","type":"trade","balance":"0.000000000000000000"},{"currency":"snt","type":"frozen","balance":"0.000000000000000000"},{"currency":"storj","type":"trade","balance":"0.000000000000000000"},{"currency":"storj","type":"frozen","balance":"0.000000000000000000"},{"currency":"swftc","type":"trade","balance":"0.000000000000000000"},{"currency":"swftc","type":"frozen","balance":"0.000000000000000000"},{"currency":"theta","type":"trade","balance":"0.000000000000000000"},{"currency":"theta","type":"frozen","balance":"0.000000000000000000"},{"currency":"tnb","type":"trade","balance":"0.000000000000000000"},{"currency":"tnb","type":"frozen","balance":"0.000000000000000000"},{"currency":"tnt","type":"trade","balance":"0.000000000000000000"},{"currency":"tnt","type":"frozen","balance":"0.000000000000000000"},{"currency":"topc","type":"trade","balance":"0.000000000000000000"},{"currency":"topc","type":"frozen","balance":"0.000000000000000000"},{"currency":"usdt","type":"trade","balance":"0.000000006000006181"},{"currency":"usdt","type":"frozen","balance":"0.000000000000000001"},{"currency":"utk","type":"trade","balance":"0.000000000000000000"},{"currency":"utk","type":"frozen","balance":"0.000000000000000000"},{"currency":"ven","type":"trade","balance":"0.000000000000000000"},{"currency":"ven","type":"frozen","balance":"0.000000000000000000"},{"currency":"wax","type":"trade","balance":"0.000000000000000000"},{"currency":"wax","type":"frozen","balance":"0.000000000000000000"},{"currency":"wicc","type":"trade","balance":"0.000000000000000000"},{"currency":"wicc","type":"frozen","balance":"0.000000000000000000"},{"currency":"xem","type":"trade","balance":"0.000000000000000000"},{"currency":"xem","type":"frozen","balance":"0.000000000000000000"},{"currency":"xrp","type":"trade","balance":"0.000000000000000000"},{"currency":"xrp","type":"frozen","balance":"0.000000000000000000"},{"currency":"yee","type":"trade","balance":"0.000000000000000000"},{"currency":"yee","type":"frozen","balance":"0.000000000000000000"},{"currency":"zec","type":"trade","balance":"0.000000000000000000"},{"currency":"zec","type":"frozen","balance":"0.000000000000000000"},{"currency":"zrx","type":"trade","balance":"0.000000000000000000"},{"currency":"zrx","type":"frozen","balance":"0.000000000000000000"}]
     */

    private long id;
    private String type;
    private String state;
    private List<BalanceBean> list;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<BalanceBean> getList() {
        return list;
    }

    public void setList(List<BalanceBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", list=" + list +
                '}';
    }

    public static class BalanceBean {
        /**
         * currency : act
         * type : trade
         * balance : 0.000000000000000000
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

        @Override
        public String toString() {
            return "BalanceBean{" +
                    "currency='" + currency + '\'' +
                    ", type='" + type + '\'' +
                    ", balance='" + balance + '\'' +
                    '}';
        }
    }
}
