package com.icer.huobitrade.http;

/**
 * Created by cljlo on 2018/1/19.
 */

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
    String V_COMMON_CURRENCYS = "/common/currencys";
    String V_COMMON_TIMESTAMP = "/common/timestamp";

    String V_ACCOUNT_ACCOUNTS = "/account/accounts";
    String V_ACCOUNT_BALACNE = "/account/accounts/%s/balance";

}
