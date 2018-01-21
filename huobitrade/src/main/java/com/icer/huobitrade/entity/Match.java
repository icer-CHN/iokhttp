package com.icer.huobitrade.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cljlo on 2018/1/21.
 */

public class Match implements Serializable {

    private long id;
    @SerializedName("order-id")
    private long orderId;
    @SerializedName("match-id")
    private long matchId;
    private String symbol;
    private String type;
    private String source;
    private String price;
    @SerializedName("filled-amount")
    private String filledAmount;
    @SerializedName("filled-fees")
    private String filledFees;
    @SerializedName("created-at")
    private long createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFilledAmount() {
        return filledAmount;
    }

    public void setFilledAmount(String filledAmount) {
        this.filledAmount = filledAmount;
    }

    public String getFilledFees() {
        return filledFees;
    }

    public void setFilledFees(String filledFees) {
        this.filledFees = filledFees;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", matchId=" + matchId +
                ", symbol='" + symbol + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", price='" + price + '\'' +
                ", filledAmount='" + filledAmount + '\'' +
                ", filledFees='" + filledFees + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
