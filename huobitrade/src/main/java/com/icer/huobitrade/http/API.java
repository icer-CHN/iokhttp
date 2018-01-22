package com.icer.huobitrade.http;

public interface API {

    /**
     * 获取K线数据
     */
    String M_K_LINE = "/history/kline";

    /**
     * 获取聚合行情(Ticker)
     */
    String M_TICKER_DETAIL = "/detail/merged";

    /**
     * 获取 Market Depth 数据
     */
    String M_DEPTH = "/depth";

    /**
     * 获取 Trade Detail 数据
     */
    String M_TRADE_DETAIL = "/trade";

    /**
     * 批量获取最近的交易记录
     */
    String M_HISTORY_TRADE = "/history/trade";

    /**
     * 获取 Market Detail 24小时成交量数据
     */
    String M_MARKET_24H_DETAIL = "/detail";


    /***********************************************TRADE************************************************/

    String V_COMMON_SYMBOLS = "/common/symbols";
    /**
     * 目前市场支持的币种
     */
    String V_COMMON_CURRENCYS = "/common/currencys";
    String V_COMMON_TIMESTAMP = "/common/timestamp";
    /**
     * 查询交易币种最大最小交易限制
     */
    String V_COMMON_EXCHANGE = "/common/exchange?symbol=%s";

    /**
     * 查询我的账号(s)
     */
    String V_ACCOUNT_ACCOUNTS = "/account/accounts";
    /**
     * 查询账号余额
     */
    String V_ACCOUNT_BALANCE = "/account/accounts/%s/balance";

    /**
     * 下单 （重要：如果使用借贷资产交易，请在下单接口/v1/order/orders/place请求参数source中填写‘margin-api’）
     */
    String V_ORDER_ORDER_PLACE = "/order/orders/place";
    String V_ORDER_CANCEL = "/order/orders/%s/submitcancel";
    /**
     * 批量取消订单
     */
    String V_ORDER_BATCH_CANCEL = "/order/orders/batchcancel";
    String V_ORDER_DETAIL = "/order/orders/%s";
    /**
     * 查询某个订单的成交明细
     */
    String V_ORDER_MATCH_RESULT = "/order/orders/%s/matchresults";
    /**
     * 查询当前委托、历史委托
     */
    String V_ORDER_ORDERS = "/order/orders";
    /**
     * 查询当前成交、历史成交
     */
    String V_ORDER_MATCH_RESULTS = "/order/matchresults";
}
