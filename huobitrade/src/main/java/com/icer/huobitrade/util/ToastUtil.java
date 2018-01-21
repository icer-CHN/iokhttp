package com.icer.huobitrade.util;

import android.widget.Toast;

import com.icer.huobitrade.app.App;


public class ToastUtil {
    public static void toastShort(String text) {
        Toast.makeText(App.getApp(), text, Toast.LENGTH_SHORT).show();
    }
}
