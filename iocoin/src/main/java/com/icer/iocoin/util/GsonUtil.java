package com.icer.iocoin.util;

import com.google.gson.Gson;


public class GsonUtil {
    private static Gson sGson;

    static {
        sGson = new Gson();
    }

    public static <T> T json2Entity(String json, Class<T> clazz) {
        T t = null;
        try {
            t = sGson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
