package com.icer.huobitrade.http.req;

import android.support.annotation.Nullable;

import com.icer.huobitrade.http.API;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.huobitrade.http.resp.DepthResp;
import com.icer.huobitrade.http.resp.KLineResp;
import com.icer.huobitrade.http.resp.MarketResp;
import com.icer.huobitrade.http.resp.TickerResp;
import com.icer.huobitrade.http.resp.TradeHistoryResp;
import com.icer.huobitrade.http.resp.TradeResp;
import com.icer.huobitrade.util.SignUtil;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;


public class ReqMarket extends Req {

    /**
     * @param symbol btcusdt, bccbtc, rcneth...
     * @param period {@link Period}
     * @param size   [1,2000] default: 150
     */
    public static void getKLine(String symbol, Period period, @Nullable Integer size, Request.EntityCallback<KLineResp> callback) {
        Request req = new AppRequestBuilder()
                .url(marketApi(API.M_K_LINE))
                .addBody("symbol", symbol)
                .addBody("period", period.getVal())
                .addBody("size", size == null ? 150 : size)
                .callback(callback)
                .build();
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    public static void getTickerDetail(String symbol, Request.EntityCallback<TickerResp> callback) {
        Request req = new AppRequestBuilder()
                .url(marketApi(API.M_TICKER_DETAIL))
                .addBody("symbol", symbol)
                .callback(callback)
                .build();
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    /**
     * @param symbol btcusdt, bccbtc, rcneth...
     * @param type   step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度 {@link DepthType}
     */
    public static void getDepth(String symbol, DepthType type, Request.EntityCallback<DepthResp> callback) {
        Request req = new AppRequestBuilder()
                .url(marketApi(API.M_DEPTH))
                .addBody("symbol", symbol)
                .addBody("type", type.getVal())
                .callback(callback)
                .build();
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    /**
     * @param symbol btcusdt, bccbtc, rcneth...
     */
    public static void getTrade(String symbol, Request.EntityCallback<TradeResp> callback) {
        Request req = new AppRequestBuilder()
                .url(marketApi(API.M_TRADE_DETAIL))
                .addBody("symbol", symbol)
                .callback(callback)
                .build();
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    /**
     * @param symbol btcusdt, bccbtc, rcneth...
     */
    public static void getTradeHistory(String symbol, Request.EntityCallback<TradeHistoryResp> callback) {
        Request req = new AppRequestBuilder()
                .url(marketApi(API.M_HISTORY_TRADE))
                .addBody("symbol", symbol)
                .callback(callback)
                .build();
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    /**
     * @param symbol btcusdt, bccbtc, rcneth...
     */
    public static void getMarket24H(String symbol, Request.EntityCallback<MarketResp> callback) {
        Request req = new AppRequestBuilder()
                .url(marketApi(API.M_MARKET_24H_DETAIL))
                .addBody("symbol", symbol)
                .callback(callback)
                .build();
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    public enum Period {
        MIN("1min"),
        MIN_5("5min"),
        MIN_15("15min"),
        MIN_30("30min"),
        MIN_60("60min"),
        DAY("1day"),
        WEEK("1week"),
        MONTH("1mon"),
        YEAR("1year");

        Period(String val) {
            this.val = val;
        }

        private String val;

        public String getVal() {
            return val;
        }
    }

    public enum DepthType {
        STEP0("step0"),
        STEP1("step1"),
        STEP2("step2"),
        STEP3("step3"),
        STEP4("step4"),
        STEP5("step5");

        DepthType(String val) {
            this.val = val;
        }

        private String val;

        public String getVal() {
            return val;
        }
    }
}
