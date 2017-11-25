package com.icer.iokhttplib;

/**
 * http管理类,统一调用类
 */

public class HttpMgr {

    private static boolean DEBUG = true;

    private static IHttp getHttpMgr() {
        return OkHttpImpl.getInstance();
    }

    public static void setDebug(boolean isDebug) {
        DEBUG = isDebug;
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void get(Request request) {
        getHttpMgr().get(request);
    }

    public static void post(Request request) {
        getHttpMgr().post(request);
    }

    public static void cancel(Request request) {
        getHttpMgr().cancel(request);
    }

    public static void cancelTag(Object tag) {
        getHttpMgr().cancelTag(tag);
    }

    public static void cancelAll() {
        getHttpMgr().cancelAll();
    }
}
