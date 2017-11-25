package com.icer.iokhttplib;

/**
 * Http 业务接口
 *
 */

public interface IHttp {

    void get(Request request);

    void post(Request request);

    void cancel(Request request);

    void cancelTag(Object tag);

    void cancelAll();
}
