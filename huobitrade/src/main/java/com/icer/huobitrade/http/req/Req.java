package com.icer.huobitrade.http.req;

import com.icer.huobitrade.app.Constants;

public class Req {

    public static String marketApi(String api) {
        return Constants.PROTOCOL + Constants.URL + Constants.PATH_MARKET + api;
    }

    public static String v1Api(String api) {
        return Constants.PROTOCOL + Constants.URL + Constants.PATH_TRADE + api;
    }
}
