package com.icer.huobitrade.http;

import com.icer.iokhttplib.Request;


public class AppRequestBuilder extends Request.Builder {

    public static final String PARAM_ACCESS_KEY_ID = "AccessKeyId";
    public static final String PARAM_SIGN_METHOD = "SignatureMethod";
    public static final String PARAM_SIGN_VERSION = "SignatureVersion";
    public static final String PARAM_TIMESTAMP = "Timestamp";
    public static final String PARAM_SIGNATURE = "Signature";

    public AppRequestBuilder() {
        //添加请求头
        addHeader("Content-Type", "application/json");
        addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
    }
}
