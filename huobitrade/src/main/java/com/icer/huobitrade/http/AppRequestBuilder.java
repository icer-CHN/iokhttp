package com.icer.huobitrade.http;

import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.util.EncodeUtil;
import com.icer.huobitrade.util.TimeUtil;
import com.icer.iokhttplib.Request;

/**
 * Created by cljlo on 2018/1/18.
 */

public class AppRequestBuilder extends Request.Builder {

    public AppRequestBuilder(boolean marketApi, String api) {
        //设置url
        url(Constants.PROTOCOL + Constants.URL + (marketApi ? Constants.PATH_MARKET : Constants.PATH_TRADE) + api);
        //添加请求头
        addHeader("Content-Type", "application/json");
        addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        //行情API之外的都要签名
        if (!marketApi) {
            String timestamp = TimeUtil.getUTCTimestamp();//获取UTC时间 格式"yyyy-MM-ddTHH:mm:ss"

            StringBuilder sb = new StringBuilder(1024);
            sb.append("POST".toUpperCase()).append('\n') // method
                    .append(Constants.URL.toLowerCase()).append('\n') // Host
                    .append(Constants.PATH_TRADE + api).append('\n'); // /path

            sb.append("AccessKeyId" + "=" + EncodeUtil.urlEncode(Constants.K1) + "&");
            sb.append("SignatureMethod" + "=" + EncodeUtil.urlEncode("HmacSHA256") + "&");
            sb.append("SignatureVersion" + "=" + EncodeUtil.urlEncode("2") + "&");
            sb.append("Timestamp" + "=" + EncodeUtil.urlEncode(timestamp));
            String signature = sb.toString();
            signature = EncodeUtil.HMACSHA256ThenBase64String(signature, Constants.K2);
            signature = EncodeUtil.urlEncode(signature);

            addBody("AccessKeyId", Constants.K1);//添加请求体
            addBody("SignatureMethod", "HmacSHA256");
            addBody("SignatureVersion", "2");
            addBody("Timestamp", timestamp);
            addBody("Signature", signature);
        }
    }
}
