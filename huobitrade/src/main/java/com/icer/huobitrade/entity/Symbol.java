package com.icer.huobitrade.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cljlo on 2018/1/21.
 */

public class Symbol implements Serializable {


    /**
     * base-currency : omg 基础币种
     * quote-currency : usdt 计价币种
     * price-precision : 2 价格精度位数（0为个位）
     * amount-precision : 4 数量精度位数（0为个位）
     * symbol-partition : main 交易区 main主区，innovation创新区，bifurcation分叉区
     */

    @SerializedName("base-currency")
    private String baseCurrency;
    @SerializedName("quote-currency")
    private String quoteCurrency;
    @SerializedName("price-precision")
    private int pricePrecision;
    @SerializedName("amount-precision")
    private int amountPrecision;
    @SerializedName("symbol-partition")
    private String symbolPartition;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public int getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(int pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public int getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision(int amountPrecision) {
        this.amountPrecision = amountPrecision;
    }

    public String getSymbolPartition() {
        return symbolPartition;
    }

    public void setSymbolPartition(String symbolPartition) {
        this.symbolPartition = symbolPartition;
    }

    public String getSymbol() {
        return baseCurrency + quoteCurrency;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", quoteCurrency='" + quoteCurrency + '\'' +
                ", pricePrecision=" + pricePrecision +
                ", amountPrecision=" + amountPrecision +
                ", symbolPartition='" + symbolPartition + '\'' +
                '}';
    }
}
