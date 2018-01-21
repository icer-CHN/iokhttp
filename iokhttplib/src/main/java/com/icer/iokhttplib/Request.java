package com.icer.iokhttplib;


import java.util.HashMap;
import java.util.Map;

/**
 * 通用网络请求类
 */
public class Request {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    private boolean mFormData;
    private String mMethod;
    private String mUrl;
    private Map<String, String> mHeader;
    private Map<String, Object> mBody;
    private Object mTag;
    @Deprecated
    private int mRetryCount;
    @Deprecated
    private int mTimeoutMillis;
    private Callback mCallback;
    private ProgressListener mRequestProgressListener;
    private ProgressListener mResponseProgressListener;

    private Request() {
    }

    public boolean isFormData() {
        return mFormData;
    }

    public String getMethod() {
        return mMethod;
    }

    public String getUrl() {
        return mUrl;
    }

    public void updateUrl(String url) {
        mUrl = url;
    }

    public Map<String, String> getHeader() {
        return mHeader;
    }

    public Map<String, Object> getBody() {
        return mBody;
    }

    public Object getTag() {
        return mTag;
    }

    @Deprecated
    public int getRetryCount() {
        return mRetryCount;
    }

    @Deprecated
    public int getTimeoutMillis() {
        return mTimeoutMillis;
    }

    public Callback getCallback() {
        return mCallback;
    }

    public ProgressListener getRequestProgressListener() {
        return mRequestProgressListener;
    }

    public ProgressListener getResponseProgressListener() {
        return mResponseProgressListener;
    }

    @Override
    public String toString() {
        return "Request{" +
                "FormData=" + mFormData +
                ", Method='" + mMethod + '\'' +
                ", Url='" + mUrl + '\'' +
                ", Header=" + mHeader +
                ", Body=" + mBody +
                ", Tag=" + mTag +
                '}';
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Request request = (Request) o;

        if (mFormData != request.mFormData) {
            return false;
        }
        if (!mUrl.equals(request.mUrl)) {
            return false;
        }
        if (!mHeader.equals(request.mHeader)) {
            return false;
        }
        if (!mBody.equals(request.mBody)) {
            return false;
        }
        if (mTag != null ? !mTag.equals(request.mTag) : request.mTag != null) {
            return false;
        }
        if (mCallback != null ? !mCallback.equals(request.mCallback) : request.mCallback != null) {
            return false;
        }
        if (mRequestProgressListener != null ? !mRequestProgressListener.equals(request.mRequestProgressListener) : request.mRequestProgressListener != null) {
            return false;
        }
        return mResponseProgressListener != null ? mResponseProgressListener.equals(request.mResponseProgressListener) : request.mResponseProgressListener == null;

    }

    @Override
    public int hashCode() {
        int result = (mFormData ? 1 : 0);
        result = 31 * result + mUrl.hashCode();
        result = 31 * result + mHeader.hashCode();
        result = 31 * result + mBody.hashCode();
        result = 31 * result + (mTag != null ? mTag.hashCode() : 0);
        result = 31 * result + (mCallback != null ? mCallback.hashCode() : 0);
        result = 31 * result + (mRequestProgressListener != null ? mRequestProgressListener.hashCode() : 0);
        result = 31 * result + (mResponseProgressListener != null ? mResponseProgressListener.hashCode() : 0);
        return result;
    }

    public static class Builder {
        private boolean formData;
        private String method = METHOD_GET;
        private String url = "";
        private Map<String, String> header = new HashMap<>();
        private Map<String, Object> body = new HashMap<>();
        private Object tag;
        @Deprecated
        private int retryCount = 0;
        @Deprecated
        private int timeoutMillis = 30 * 1000;
        private Callback callback;
        private ProgressListener requestProgressListener;
        private ProgressListener responseProgressListener;

        public Builder formData(boolean formData) {
            this.formData = formData;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.header.put(key, value);
            return this;
        }

        public Builder header(Map<String, String> header) {
            if (header != null) {
                this.header = header;
            }
            return this;
        }

        /**
         * @param value digit，String，File
         */
        public Builder addBody(String key, Object value) {
            this.body.put(key, value);
            return this;
        }

        /**
         * @param body digit，String，File
         */
        public Builder body(Map<String, Object> body) {
            if (body != null) {
                this.body = body;
            }
            return this;
        }

        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        @Deprecated
        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        @Deprecated
        public Builder timeoutMillis(int timeoutMillis) {
            this.timeoutMillis = timeoutMillis;
            return this;
        }

        public Builder callback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public Builder requestProgressListener(ProgressListener progressListener) {
            this.requestProgressListener = progressListener;
            return this;
        }

        public Builder responseProgressListener(ProgressListener progressListener) {
            this.responseProgressListener = progressListener;
            return this;
        }

        public Request build() {
            Request req = new Request();
            req.mFormData = formData;
            req.mMethod = method;
            req.mUrl = url;
            req.mHeader = header;
            req.mBody = body;
            req.mTag = tag;
            req.mRetryCount = retryCount;
            req.mTimeoutMillis = timeoutMillis;
            req.mCallback = callback;
            req.mRequestProgressListener = requestProgressListener;
            req.mResponseProgressListener = responseProgressListener;
            return req;
        }
    }

    public interface ProgressListener {
        /**
         * 通常在Woker线程被调用，如果需要操控UI，你懂的（笑）
         *
         * @param now   当前进度
         * @param total 总进度
         * @param done  是否完成
         */
        void onProgress(long now, long total, boolean done);
    }

    public static abstract class EntityCallback<T> extends StringCallBack {
        private Class<T> mClass;

        public EntityCallback(Class<T> clazz) {
            mClass = clazz;
        }

        public final Class<T> getEntity() {
            return mClass;
        }

        public abstract void onEntity(T entity);
    }

    public static abstract class StringCallBack implements Callback<String> {
        @Override
        public void onOk(String content) {

        }

        @Override
        public void onError(Request request, Exception e) {

        }
    }

    /**
     * 基础Callback接口定义，不推荐直接使用；
     * 请使用 {@link StringCallBack}或 {@link EntityCallback}
     */
    public interface Callback<T> {
        /**
         * 正常响应
         *
         * @param content 响应内容
         */
        void onOk(T content);

        /**
         * 出错响应
         *
         * @param request 出错请求
         * @param e       异常
         */
        void onError(Request request, Exception e);
    }
}
