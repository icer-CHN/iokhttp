package com.icer.huobitrade.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.icer.huobitrade.app.App;

/**
 * Created by cljlo on 2018/1/21.
 */

public class SpUtil {
    private static SharedPreferences sSp;

    static {
        sSp = App.getApp().getSharedPreferences(App.getApp().getPackageName(), Context.MODE_PRIVATE);
    }

    public static void setString(String key, String value) {
        sSp.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        return sSp.getString(key, defValue);
    }

    public static void setInt(String key, int value) {
        sSp.edit().putInt(key, value).apply();
    }

    public static int getLong(String key, int defValue) {
        return sSp.getInt(key, defValue);
    }

    public static void setLong(String key, long value) {
        sSp.edit().putLong(key, value).apply();
    }

    public static long getLong(String key, long defValue) {
        return sSp.getLong(key, defValue);
    }

    public static void setFloat(String key, float value) {
        sSp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key, float defValue) {
        return sSp.getFloat(key, defValue);
    }

    public static void setBoolean(String key, boolean value) {
        sSp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sSp.getBoolean(key, defValue);
    }
}
