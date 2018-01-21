package com.icer.huobitrade.http.req;

import com.icer.huobitrade.http.API;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.huobitrade.http.resp.CurrencysResp;
import com.icer.huobitrade.http.resp.SymbolsResp;
import com.icer.huobitrade.http.resp.LongResp;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

/**
 * Created by cljlo on 2018/1/20.
 */

public class ReqCommon extends Req {

    public static void getSymbols(Request.EntityCallback<SymbolsResp> callback) {
        Request req = new AppRequestBuilder()
                .url(v1Api(API.V_COMMON_SYMBOLS))
                .callback(callback)
                .build();
        HttpMgr.request(req);
    }

    public static void getCurrencys(Request.EntityCallback<CurrencysResp> callback) {
        Request req = new AppRequestBuilder()
                .url(v1Api(API.V_COMMON_CURRENCYS))
                .callback(callback)
                .build();
        HttpMgr.request(req);
    }

    public static void getTimestamp(Request.EntityCallback<LongResp> callback) {
        Request req = new AppRequestBuilder()
                .url(v1Api(API.V_COMMON_TIMESTAMP))
                .callback(callback)
                .build();
        HttpMgr.request(req);
    }
}
