package com.icer.huobitrade.http;

import com.icer.huobitrade.Constants;
import com.icer.huobitrade.util.EncodeUtil;
import com.icer.huobitrade.util.TimeUtil;
import com.icer.iokhttplib.Request;

/**
 * Created by cljlo on 2018/1/18.
 */

public class AppRequestBuilder extends Request.Builder {

    public AppRequestBuilder(String path, String api) {
        String timestamp = TimeUtil.getUTCTimestamp();
        String signature = "POST\n"
                + Constants.URL + "\n"
                + path + api + "\n"
                + "AccessKeyId=" + EncodeUtil.uriEncode(Constants.K1) + "&"
                + "SignatureMethod=" + EncodeUtil.uriEncode("HmacSHA256") + "&"
                + "SignatureVersion=" + EncodeUtil.uriEncode("2") + "&"
                + "Timestamp=" + EncodeUtil.uriEncode(timestamp);
        signature = EncodeUtil.HMACSHA256(signature.getBytes(), Constants.K2.getBytes());
        signature = EncodeUtil.base64Encoder(signature);

        url(Constants.PROTOCOL + Constants.URL + path + api);
        addHeader("Content-Type","application/json");

        addBody("AccessKeyId", Constants.K1);
        addBody("SignatureMethod", "HmacSHA256");
        addBody("SignatureVersion", "2");
        addBody("Timestamp", timestamp);
        addBody("Signature", signature);
    }
}
