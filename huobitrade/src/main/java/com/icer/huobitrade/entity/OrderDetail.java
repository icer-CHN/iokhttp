package com.icer.huobitrade.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class OrderDetail implements Serializable {

    /**
     * id : 842137371
     * symbol : eosusdt
     * account-id : 1645829
     * amount : 1171.355598530000000000
     * price : 0.0
     * created-at : 1516511061854
     * type : buy-market
     * field-amount : 89.280152326981707300
     * field-cash-amount : 1171.355598529999999776
     * field-fees : 0.178560304653963414
     * finished-at : 1516511062347
     * source : margin-web
     * state : filled
     * canceled-at : 0
     */

    private long id;
    private String symbol;
    @SerializedName("account-id")
    private long accountId;
    private String amount;
    private String price;
    @SerializedName("created-at")
    private long createdAt;
    private String type;
    @SerializedName("field-amount")
    private String fieldAmount;
    @SerializedName("field-cash-amount")
    private String fieldCashAmount;
    @SerializedName("field-fees")
    private String fieldFees;
    @SerializedName("finished-at")
    private long finishedAt;
    private String source;
    private String state;
    @SerializedName("canceled-at")
    private long canceledAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldAmount() {
        return fieldAmount;
    }

    public void setFieldAmount(String fieldAmount) {
        this.fieldAmount = fieldAmount;
    }

    public String getFieldCashAmount() {
        return fieldCashAmount;
    }

    public void setFieldCashAmount(String fieldCashAmount) {
        this.fieldCashAmount = fieldCashAmount;
    }

    public String getFieldFees() {
        return fieldFees;
    }

    public void setFieldFees(String fieldFees) {
        this.fieldFees = fieldFees;
    }

    public long getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(long canceledAt) {
        this.canceledAt = canceledAt;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", accountId=" + accountId +
                ", amount='" + amount + '\'' +
                ", price='" + price + '\'' +
                ", createdAt=" + createdAt +
                ", type='" + type + '\'' +
                ", fieldAmount='" + fieldAmount + '\'' +
                ", fieldCashAmount='" + fieldCashAmount + '\'' +
                ", fieldFees='" + fieldFees + '\'' +
                ", finishedAt=" + finishedAt +
                ", source='" + source + '\'' +
                ", state='" + state + '\'' +
                ", canceledAt=" + canceledAt +
                '}';
    }
}
