package com.icer.huobitrade.util;

import com.google.gson.Gson;

public class JsonUtil {
    private static Gson sGson;

    static {
        sGson = new Gson();
    }

    public static String toJson(Object obj) {
        return sGson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return sGson.fromJson(json, tClass);
    }
}
