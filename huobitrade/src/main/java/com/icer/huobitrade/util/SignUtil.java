package com.icer.huobitrade.util;

import android.util.Log;

import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.iokhttplib.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by cljlo on 2018/1/20.
 */

public class SignUtil {
    public static void addSignature(Request request) {
        addSignature(request, false);
    }

    public static void addSignature(Request request, boolean urlEncode) {
        Map<String, Object> params = request.getBody();
        String url = request.getUrl();

        String host = url.replace(Constants.PROTOCOL, "");
        host = host.substring(0, host.indexOf("/"));

        String apiPath = url.replace(Constants.PROTOCOL + host, "");

        StringBuilder sb = new StringBuilder(1024);
        sb.append(request.getMethod().toUpperCase()).append('\n') // method
                .append(host.toLowerCase()).append('\n') // Host
                .append(apiPath).append('\n'); // /path

        List<Map.Entry<String, Object>> paramsList = new ArrayList<>();
        paramsList.addAll(params.entrySet());
        Collections.sort(paramsList, new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<String, Object> e : paramsList) {
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
            sb.append(e.getKey() + "=" + EncodeUtil.urlEncode(e.getValue().toString()) + "&");
        }
        sb.deleteCharAt(sb.length() - 1);
        String signature = sb.toString();
        Log.i("Signature", signature);
        signature = EncodeUtil.HMACSHA256ThenBase64String(signature, Constants.K2);
        signature = signature.replaceAll("\\+", "%2B");
        Log.i("Signature", signature);
        if (urlEncode) {
            signature = EncodeUtil.urlEncode(signature);
            Log.i("Signature", signature);
        }
        request.getBody().put("Signature", signature);
    }

    /**
     * 针对GET请求，拼接参数到url后面
     *
     * @param request
     * @return 拼接好的url
     */
    public static String urlJoinParams(Request request) {
        String url = request.getUrl() + "?";
        Map<String, Object> params = request.getBody();
        for (Map.Entry<String, Object> e : params.entrySet()) {
//            url += e.getKey() + "=" + EncodeUtil.urlEncode(e.getValue().toString()) + "&";
            url += e.getKey() + "=" + e.getValue().toString() + "&";
        }
        url = url.substring(0, url.length() - 1);
        Log.i("URL", url);
        return url;
    }
}
