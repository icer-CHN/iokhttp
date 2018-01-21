package com.icer.huobitrade.http.req;

import android.text.TextUtils;

import com.icer.huobitrade.http.API;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.huobitrade.http.resp.BatchCancelResp;
import com.icer.huobitrade.http.resp.MatchsResp;
import com.icer.huobitrade.http.resp.OrderDetailResp;
import com.icer.huobitrade.http.resp.OrderMatchResp;
import com.icer.huobitrade.http.resp.OrdersResp;
import com.icer.huobitrade.http.resp.StringResp;
import com.icer.huobitrade.util.SignUtil;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class ReqOrder extends Req {

    /**
     * 下单
     *
     * @param accountId 账户 ID，使用accounts方法获得。币币交易使用‘spot’账户的accountid；借贷资产交易，请使用‘margin’账户的accountid
     * @param amount    限价单表示下单数量，市价买单时表示买多少钱，市价卖单时表示卖多少币
     * @param price     下单价格，市价单不传该参数
     * @param isMargin  是否为借贷账户交易，api，如果使用借贷资产交易，请填写‘margin-api’
     * @param symbol    交易对
     * @param type      订单类型 buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖 {@link OrderType}
     * @param callback
     */
    public static void place(String accountId, String amount, String price, boolean isMargin, String symbol, OrderType type, Request.EntityCallback<StringResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .method(Request.METHOD_POST)
                .jsonBody(true)
                .url(v1Api(API.V_ORDER_ORDER_PLACE))
                .addBody("account-id", accountId)
                .addBody("amount", amount)
                .addBody("symbol", symbol)
                .addBody("type", type.getVal())
                .callback(callback);
        if (!TextUtils.isEmpty(price) && OrderType.BUY_MARKET != type && OrderType.SELL_MARKET != type) {
            rb.addBody("price", price);
        }
        if (isMargin) {
            rb.addBody("source", "margin-api");
        }
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req, Request.METHOD_POST));
        HttpMgr.request(req);
    }

    public static void cancel(String orderId, Request.EntityCallback<StringResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .method(Request.METHOD_POST)
                .jsonBody(true)
                .url(v1Api(String.format(API.V_ORDER_CANCEL, orderId)))
                .addBody("order-id", orderId)
                .callback(callback);
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req, Request.METHOD_POST));
        HttpMgr.request(req);
    }

    /**
     * @param orderIds 单次不超过50个订单id
     * @param callback
     */
    public static void cancelBatch(List<String> orderIds, Request.EntityCallback<BatchCancelResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .method(Request.METHOD_POST)
                .jsonBody(true)
                .url(v1Api(API.V_ORDER_BATCH_CANCEL))
                .addBody("order-ids", orderIds)
                .callback(callback);
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req, Request.METHOD_POST));
        HttpMgr.request(req);
    }

    public static void orderDetail(String orderId, Request.EntityCallback<OrderDetailResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .url(v1Api(String.format(API.V_ORDER_DETAIL, orderId)))
                .callback(callback);
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    public static void orderMatch(String orderId, Request.EntityCallback<OrderMatchResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .url(v1Api(String.format(API.V_ORDER_MATCH_RESULT, orderId)))
                .callback(callback);
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    /**
     * @param symbol 交易对
     * @param states 查询的订单状态组合，使用','分割  pre-submitted 准备提交, submitted 已提交, partial-filled 部分成交, partial-canceled 部分成交撤销, filled 完全成交, canceled 已撤销
     */
    public static void orders(String symbol, OrderState states, Request.EntityCallback<OrdersResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .url(v1Api(API.V_ORDER_ORDERS))
                .addBody("symbol", symbol)
                .addBody("states", states.getVal())
                .callback(callback);
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    /**
     * 查询当前成交、历史成交
     *
     * @param symbol 交易对
     */
    public static void matchResults(String symbol, Request.EntityCallback<MatchsResp> callback) {
        Request.Builder rb = new AppRequestBuilder()
                .url(v1Api(API.V_ORDER_MATCH_RESULTS))
                .addBody("symbol", symbol)
                .callback(callback);
        Request req = rb.build();
        SignUtil.addSignature(req);
        req.updateUrl(SignUtil.urlJoinParams(req));
        HttpMgr.request(req);
    }

    public enum OrderState {
        PRE_SUBMIT("pre-submitted"),//准备提交
        SUBMITTED("submitted"),
        PARTIAL_FILLED("partial-filled"),//部分成交
        PARTIAL_CANCELED("partial-canceled"),//部分撤销
        FILLED("filled"),//已成交
        CANCELED("canceled");//已撤销

        OrderState(String val) {
            this.val = val;
        }

        private String val;

        public String getVal() {
            return val;
        }
    }

    public enum OrderType {
        BUY_MARKET("buy-market"),
        SELL_MARKET("sell-market"),
        BUY_LIMIT("buy-limit"),
        SELL_LIMIT("sell-limit");

        OrderType(String val) {
            this.val = val;
        }

        private String val;

        public String getVal() {
            return val;
        }
    }
}
