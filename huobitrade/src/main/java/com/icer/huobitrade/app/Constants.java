package com.icer.huobitrade.app;


public interface Constants {

    String SP_KEY_SYMBOL = "SYMBOL";
    String SP_KEY_ACCOUNT = "ACCOUNT";
    String SP_KEY_APPKEY = "APPKEY";
    String SP_KEY_SECRET = "SECRET";
    String SP_KEY_ALERTOVER_SENDER = "ALERTOVER_SENDER";
    String SP_KEY_ALERTOVER_RECEIVER = "ALERTOVER_RECEIVER";

    String PROTOCOL = "https://";
    String URL = "api.huobi.pro";
    String PATH_MARKET = "/market";
    String PATH_TRADE = "/v1";


    String URL_ALERT_OVER = "https://api.alertover.com/v1/alert";


    /******************************本地广播KEY******************************/
    String EXTRA_LB_STATUS = "EXTRA_LB_STATUS";//广播状态(约定为int)
    String EXTRA_LB_FLAG = "EXTRA_LB_FLAG";//广播flag（约定为boolean）
    String EXTRA_LB_DATA = "EXTRA_LB_DATA";//广播数据（自定义）

    String LB_LOGIN = "LB_LOGIN";//登录账户
    String LB_NEW_TICKER = "LB_NEW_TICKER";//最新市值变动
}
