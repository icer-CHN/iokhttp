package com.icer.huobitrade.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ExchangeLimits implements Serializable {

    /**
     * symbol : eosusdt
     * buy-limit-must-less-than : 2
     * sell-limit-must-greater-than : 0.5
     * limit-order-must-greater-than : 0.01
     * limit-order-must-less-than : 1000000.0
     * market-buy-order-must-greater-than : 0.1
     * market-buy-order-must-less-than : 1000000.0
     * market-sell-order-must-greater-than : 0.01
     * market-sell-order-must-less-than : 100000
     * circuit-break-when-greater-than : 10000
     * circuit-break-when-less-than : 10
     * circuit-break-rate : 0
     */

    private String symbol;
    @SerializedName("buy-limit-must-less-than")
    private double buyLimitMLT;
    @SerializedName("sell-limit-must-greater-than")
    private double sellLimitMGT;
    @SerializedName("limit-order-must-greater-than")
    private double limitOrderMGT;
    @SerializedName("limit-order-must-less-than")
    private double limitOrderMLT;
    @SerializedName("market-buy-order-must-greater-than")
    private double marketBuyOrderMGT;
    @SerializedName("market-buy-order-must-less-than")
    private double marketBuyOrderMLT;
    @SerializedName("market-sell-order-must-greater-than")
    private double marketSellOrderMGT;
    @SerializedName("market-sell-order-must-less-than")
    private double marketSellOrderMLT;
    @SerializedName("circuit-break-when-greater-than")
    private double circuitBreakWGT;
    @SerializedName("circuit-break-when-less-than")
    private double circuitBreakWLT;
    @SerializedName("circuit-break-rate")
    private double circuitBreakRate;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getBuyLimitMLT() {
        return buyLimitMLT;
    }

    public void setBuyLimitMLT(double buyLimitMLT) {
        this.buyLimitMLT = buyLimitMLT;
    }

    public double getSellLimitMGT() {
        return sellLimitMGT;
    }

    public void setSellLimitMGT(double sellLimitMGT) {
        this.sellLimitMGT = sellLimitMGT;
    }

    public double getLimitOrderMGT() {
        return limitOrderMGT;
    }

    public void setLimitOrderMGT(double limitOrderMGT) {
        this.limitOrderMGT = limitOrderMGT;
    }

    public double getLimitOrderMLT() {
        return limitOrderMLT;
    }

    public void setLimitOrderMLT(double limitOrderMLT) {
        this.limitOrderMLT = limitOrderMLT;
    }

    public double getMarketBuyOrderMGT() {
        return marketBuyOrderMGT;
    }

    public void setMarketBuyOrderMGT(double marketBuyOrderMGT) {
        this.marketBuyOrderMGT = marketBuyOrderMGT;
    }

    public double getMarketBuyOrderMLT() {
        return marketBuyOrderMLT;
    }

    public void setMarketBuyOrderMLT(double marketBuyOrderMLT) {
        this.marketBuyOrderMLT = marketBuyOrderMLT;
    }

    public double getMarketSellOrderMGT() {
        return marketSellOrderMGT;
    }

    public void setMarketSellOrderMGT(double marketSellOrderMGT) {
        this.marketSellOrderMGT = marketSellOrderMGT;
    }

    public double getMarketSellOrderMLT() {
        return marketSellOrderMLT;
    }

    public void setMarketSellOrderMLT(double marketSellOrderMLT) {
        this.marketSellOrderMLT = marketSellOrderMLT;
    }

    public double getCircuitBreakWGT() {
        return circuitBreakWGT;
    }

    public void setCircuitBreakWGT(double circuitBreakWGT) {
        this.circuitBreakWGT = circuitBreakWGT;
    }

    public double getCircuitBreakWLT() {
        return circuitBreakWLT;
    }

    public void setCircuitBreakWLT(double circuitBreakWLT) {
        this.circuitBreakWLT = circuitBreakWLT;
    }

    public double getCircuitBreakRate() {
        return circuitBreakRate;
    }

    public void setCircuitBreakRate(double circuitBreakRate) {
        this.circuitBreakRate = circuitBreakRate;
    }

    @Override
    public String toString() {
        return "ExchangeLimits{" +
                "symbol='" + symbol + '\'' +
                ", buyLimitMLT=" + buyLimitMLT +
                ", sellLimitMGT=" + sellLimitMGT +
                ", limitOrderMGT=" + limitOrderMGT +
                ", limitOrderMLT=" + limitOrderMLT +
                ", marketBuyOrderMGT=" + marketBuyOrderMGT +
                ", marketBuyOrderMLT=" + marketBuyOrderMLT +
                ", marketSellOrderMGT=" + marketSellOrderMGT +
                ", marketSellOrderMLT=" + marketSellOrderMLT +
                ", circuitBreakWGT=" + circuitBreakWGT +
                ", circuitBreakWLT=" + circuitBreakWLT +
                ", circuitBreakRate=" + circuitBreakRate +
                '}';
    }
}
