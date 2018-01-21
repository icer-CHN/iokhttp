package com.icer.huobitrade.util;

import com.google.gson.Gson;

/**
 * Created by cljlo on 2018/1/21.
 */

public class JsonUtil {
    private static Gson sGson = new Gson();

    public static String toJson(Object o) {
        return sGson.toJson(o);
    }
}
