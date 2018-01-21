package com.icer.huobitrade.util;

import android.util.Log;

import com.icer.huobitrade.app.App;
import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.iokhttplib.Request;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignUtil {
    public static void addSignature(Request request) {
        addSignature(request, false);
    }

    public static void addSignature(Request request, boolean urlEncode) {
        Map<String, Object> params = request.getBody();

        params.put(AppRequestBuilder.PARAM_ACCESS_KEY_ID, App.getApp().getAppkey());
        params.put(AppRequestBuilder.PARAM_SIGN_METHOD, "HmacSHA256");
        params.put(AppRequestBuilder.PARAM_SIGN_VERSION, "2");
        params.put(AppRequestBuilder.PARAM_TIMESTAMP, TimeUtil.getUTCTimestamp());

        String url = request.getUrl();

        String host = url.replace(Constants.PROTOCOL, "");
        host = host.substring(0, host.indexOf("/"));

        String apiPath = url.replace(Constants.PROTOCOL + host, "");

        StringBuilder sb = new StringBuilder(1024);
        sb.append(request.getMethod().toUpperCase()).append('\n') // method
                .append(host.toLowerCase()).append('\n') // Host
                .append(apiPath).append('\n'); // /path

        SortedMap<String, Object> sMap = new TreeMap<>(params);
        for (Map.Entry<String, Object> e : sMap.entrySet()) {
            if (Request.METHOD_POST.equalsIgnoreCase(request.getMethod())) {
                switch (e.getKey()) {
                    case AppRequestBuilder.PARAM_ACCESS_KEY_ID:
                    case AppRequestBuilder.PARAM_SIGN_METHOD:
                    case AppRequestBuilder.PARAM_SIGN_VERSION:
                    case AppRequestBuilder.PARAM_TIMESTAMP: {
                        break;
                    }
                    default:
                        continue;
                }
            }
            sb.append(e.getKey()).append("=").append(EncodeUtil.urlEncode(e.getValue().toString())).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        String signature = sb.toString();
        Log.i("Signature", signature);
        signature = EncodeUtil.HMACSHA256ThenBase64String(signature, App.getApp().getSecret());
        signature = signature.replaceAll("\\+", "%2B");
        Log.i("Signature", signature);
        if (urlEncode) {
            signature = EncodeUtil.urlEncode(signature);
            Log.i("Signature", signature);
        }
        request.getBody().put(AppRequestBuilder.PARAM_SIGNATURE, signature);
    }

    /**
     * 针对GET请求，拼接参数到url后面
     *
     * @param request
     * @return 拼接好的url
     */
    public static String urlJoinParams(Request request) {
        return urlJoinParams(request, Request.METHOD_GET);
    }

    public static String urlJoinParams(Request request, String method) {
        String url = request.getUrl() + "?";
        Map<String, Object> params = request.getBody();
        for (Map.Entry<String, Object> e : params.entrySet()) {
            if (Request.METHOD_POST.equalsIgnoreCase(method)) {
                switch (e.getKey()) {
                    case AppRequestBuilder.PARAM_ACCESS_KEY_ID:
                    case AppRequestBuilder.PARAM_SIGN_METHOD:
                    case AppRequestBuilder.PARAM_SIGN_VERSION:
                    case AppRequestBuilder.PARAM_TIMESTAMP:
                    case AppRequestBuilder.PARAM_SIGNATURE: {
                        break;
                    }
                    default:
                        continue;
                }
            }
            url += e.getKey() + "=" + e.getValue().toString() + "&";
        }
        url = url.substring(0, url.length() - 1);
        Log.i("URL", url);
        return url;
    }
}
