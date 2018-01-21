package com.icer.huobitrade.http.req;

import com.icer.huobitrade.http.API;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.huobitrade.http.resp.BalanceResp;
import com.icer.huobitrade.http.resp.AccountsResp;
import com.icer.huobitrade.util.SignUtil;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

/**
 * Created by cljlo on 2018/1/20.
 */

public class ReqAccount extends Req {

    public static void getAccounts(Request.EntityCallback<AccountsResp> callback) {
        Request req = new AppRequestBuilder()
                .url(v1Api(API.V_ACCOUNT_ACCOUNTS))
                .callback(callback)
                .build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    public static void getBalance(String accountId, Request.EntityCallback<BalanceResp> callback) {
        Request req = new AppRequestBuilder()
                .url(v1Api(String.format(API.V_ACCOUNT_BALANCE, accountId)))
                .callback(callback)
                .build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }
}
