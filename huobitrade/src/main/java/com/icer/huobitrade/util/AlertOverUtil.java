package com.icer.huobitrade.util;

import com.icer.huobitrade.app.App;
import com.icer.huobitrade.app.Constants;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

public class AlertOverUtil {

    public static void sendAlert(String title, String content) {
        HttpMgr.post(new Request.Builder()
                .url(Constants.URL_ALERT_OVER)
                .addBody("source", App.getApp().getAlertOverSender())
                .addBody("receiver", App.getApp().getAlertOverReceiver())
                .addBody("title", title)
                .addBody("content", content)
                .build());
    }
}
