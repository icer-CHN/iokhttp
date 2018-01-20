package com.icer.huobitrade.http;

import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.util.TimeUtil;
import com.icer.iokhttplib.Request;

/**
 * Created by cljlo on 2018/1/18.
 */

public class AppRequestBuilder extends Request.Builder {

    public AppRequestBuilder() {
        this(false);
    }

    public AppRequestBuilder(boolean sign) {
        //添加请求头
        addHeader("Content-Type", "application/json");
        addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        //行情API之外的都要签名
        if (sign) {
            addBody("AccessKeyId", Constants.K1);
            addBody("SignatureMethod", "HmacSHA256");
            addBody("SignatureVersion", "2");
            addBody("Timestamp", TimeUtil.getUTCTimestamp());
        }
    }
}
