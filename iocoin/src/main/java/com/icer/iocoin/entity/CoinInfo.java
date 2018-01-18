package com.icer.iocoin.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cljlo on 2018/1/14.
 */

public class CoinInfo implements Serializable {

    /**
     * id : eos
     * name : EOS
     * symbol : EOS
     * rank : 10
     * price_usd : 15.5411
     * price_btc : 0.00108101
     * 24h_volume_usd : 3190890000.0
     * market_cap_usd : 9340802451.0
     * available_supply : 601038694.0
     * total_supply : 900000000.0
     * max_supply : 1000000000.0
     * percent_change_1h : -1.9
     * percent_change_24h : 10.36
     * percent_change_7d : 41.76
     * last_updated : 1515863051
     * price_cny : 100.273508365
     * 24h_volume_cny : 20588100913.5
     * market_cap_cny : 60268258532.0
     */

    private String id;
    private String name;
    private String symbol;
    private String rank;
    @SerializedName("price_usd")
    private String priceUsd;
    @SerializedName("price_btc")
    private String priceBtc;
    @SerializedName("24h_volume_usd")
    private String dayVolumeUsd;
    @SerializedName("market_cap_usd")
    private String marketCapUsd;
    @SerializedName("available_supply")
    private String availableSupply;
    @SerializedName("total_supply")
    private String totalSupply;
    @SerializedName("max_supply")
    private String maxSupply;
    @SerializedName("percent_change_1h")
    private String percentChange1h;
    @SerializedName("percent_change_24h")
    private String percentChange24h;
    @SerializedName("percent_change_7d")
    private String percentChange7d;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("price_cny")
    private String priceCny;
    @SerializedName("24h_volume_cny")
    private String dayVolumeCny;
    @SerializedName("market_cap_cny")
    private String marketCapCny;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String getDayVolumeUsd() {
        return dayVolumeUsd;
    }

    public void setDayVolumeUsd(String dayVolumeUsd) {
        this.dayVolumeUsd = dayVolumeUsd;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(String availableSupply) {
        this.availableSupply = availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getPriceCny() {
        return priceCny;
    }

    public void setPriceCny(String priceCny) {
        this.priceCny = priceCny;
    }

    public String getDayVolumeCny() {
        return dayVolumeCny;
    }

    public void setDayVolumeCny(String dayVolumeCny) {
        this.dayVolumeCny = dayVolumeCny;
    }

    public String getMarketCapCny() {
        return marketCapCny;
    }

    public void setMarketCapCny(String marketCapCny) {
        this.marketCapCny = marketCapCny;
    }

    public boolean isLatestThan(CoinInfo coinInfo) {
        long thisDt = Long.parseLong(lastUpdated);
        long thatDt = Long.parseLong(coinInfo.lastUpdated);
        return thisDt > thatDt;
    }

    @Override
    public String toString() {
        return "CoinInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank='" + rank + '\'' +
                ", priceUsd='" + priceUsd + '\'' +
                ", priceBtc='" + priceBtc + '\'' +
                ", dayVolumeUsd='" + dayVolumeUsd + '\'' +
                ", marketCapUsd='" + marketCapUsd + '\'' +
                ", availableSupply='" + availableSupply + '\'' +
                ", totalSupply='" + totalSupply + '\'' +
                ", maxSupply='" + maxSupply + '\'' +
                ", percentChange1h='" + percentChange1h + '\'' +
                ", percentChange24h='" + percentChange24h + '\'' +
                ", percentChange7d='" + percentChange7d + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", priceCny='" + priceCny + '\'' +
                ", dayVolumeCny='" + dayVolumeCny + '\'' +
                ", marketCapCny='" + marketCapCny + '\'' +
                '}';
    }
}
