package com.icer.iokhttplib;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp的网络封装
 * Created on 2017/11/24.
 *
 * @author clj
 */

@SuppressWarnings({"WeakerAccess", "JavaDoc", "unchecked", "UnusedParameters"})
public class OkHttpImpl implements IHttp {

    public static final String TAG = OkHttpImpl.class.getSimpleName();
    private static final int TIMEOUT_CONNECT = 30;
    private static final int TIMEOUT_READ = 30;
    private static final int TIMEOUT_WRITE = 30;

    private final static OkHttpImpl INSTANCE;

    private final static List<Request> REQUEST_LIST;
    private final static Map<Request, Call> CALL_MAP;
    private final static Map<Request, okhttp3.Request> RESPONSE_PROGRESS_MAP;

    static {
        INSTANCE = new OkHttpImpl();
        REQUEST_LIST = new ArrayList<>();
        CALL_MAP = new HashMap<>();
        RESPONSE_PROGRESS_MAP = new HashMap<>();
    }

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;

    {
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final okhttp3.Request okRequest = chain.request();
                        //拦截
                        Response originalResponse = chain.proceed(okRequest);
                        //包装响应体并返回
                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body(), new Request.ProgressListener() {
                                    @Override
                                    public void onProgress(long now, long total, boolean done) {
                                        Set<Request> keys = RESPONSE_PROGRESS_MAP.keySet();
                                        if (keys.size() == 0) {
                                            return;
                                        }
                                        for (Request request : keys) {
                                            okhttp3.Request okReq = RESPONSE_PROGRESS_MAP.get(request);
                                            if (compareOkHttp3Request(okReq, okRequest)) {
                                                Request.ProgressListener listener = request.getResponseProgressListener();
                                                if (listener != null) {
                                                    listener.onProgress(now, total, done);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }))
                                .build();
                    }
                })
                .build();
        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    public static IHttp getInstance() {
        return INSTANCE;
    }

    @Override
    public void get(final Request request) {
        i("get()-->" + request.toString(), TAG);
        synchronized (REQUEST_LIST) {
            synchronized (CALL_MAP) {
                REQUEST_LIST.add(request);
                okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
                addHeaders(request, builder);
                builder.get().url(request.getUrl()).tag(request.getTag());
                okhttp3.Request okRequest = builder.build();
                addResponseProgress(okRequest, request);
                call(request, okRequest);
            }
        }
    }

    @Override
    public void post(final Request request) {
        i("post()-->" + request.toString(), TAG);
        synchronized (REQUEST_LIST) {
            synchronized (CALL_MAP) {
                REQUEST_LIST.add(request);
                okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
                addHeaders(request, builder);
                RequestBody rb = addPostParams(request);
                builder.post(rb).url(request.getUrl()).tag(request.getTag());
                okhttp3.Request okRequest = builder.build();
                addResponseProgress(okRequest, request);
                call(request, okRequest);
            }
        }
    }

    /**
     * 添加 Http请求头
     *
     * @param request
     * @param builder
     */
    private void addHeaders(Request request, okhttp3.Request.Builder builder) {
        if (request.getHeader().size() > 0) {
            Map<String, String> headers = request.getHeader();
            Set<String> keySet = headers.keySet();
            for (String key : keySet) {
                String head = headers.get(key);
                builder.header(key, head);
            }
        }
    }

    private RequestBody addPostParams(Request request) {
        //普通post
        if (!request.isFormData()) {
            FormBody.Builder builder = new FormBody.Builder();
            Map<String, Object> body = request.getBody();
            Set<String> keySet = body.keySet();
            for (String key : keySet) {
                Object obj = body.get(key);
                builder.add(key, obj.toString());
            }
            if (request.getRequestProgressListener() != null) {
                return new ProgressRequestBody(builder.build(), request.getRequestProgressListener());
            } else {
                return builder.build();
            }
        } else {//上传文件post
            MultipartBody.Builder builder = new MultipartBody.Builder();
            Map<String, Object> body = request.getBody();
            Set<String> keySet = body.keySet();
            for (String key : keySet) {
                Object obj = body.get(key);
                if (obj instanceof File) {
                    File file = (File) obj;
                    RequestBody frb = RequestBody.create(MediaType.parse(guessMimeType(file.getPath())), file);
                    builder.addFormDataPart(key, file.getName(), frb);
                } else {
                    builder.addFormDataPart(key, obj.toString());
                }
            }
            if (request.getRequestProgressListener() != null) {
                return new ProgressRequestBody(builder.build(), request.getRequestProgressListener());
            } else {
                return builder.build();
            }
        }
    }

    private void call(final Request request, final okhttp3.Request okRequest) {
        Call call = mOkHttpClient.newCall(okRequest);
        CALL_MAP.put(request, call);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                proxyFailure(request, okRequest, e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                proxyResponse(request, response);
            }
        });
    }

    private void addResponseProgress(okhttp3.Request okRequest, final Request request) {
        if (request.getResponseProgressListener() != null) {
            RESPONSE_PROGRESS_MAP.put(request, okRequest);
        }
    }

    private void proxyResponse(final Request request, Response response) {
        i("proxyResponse()-->" + response.toString(), TAG);
        if (needProxy(request)) {
            try {
                final String content = response.body().string();
                i("proxyResponse()-->" + content, TAG);

                final Request.Callback callback = request.getCallback();

                if (callback instanceof Request.EntityCallback) {
                    final Request.EntityCallback entityCallback = (Request.EntityCallback) callback;
                    final Class cls = entityCallback.getEntity();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            entityCallback.onOk(content);
                            try {
                                Object obj = mGson.fromJson(content, cls);
                                entityCallback.onEntity(obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                                callback.onError(request, e);
                            }
                        }
                    });
                } else if (callback instanceof Request.StringCallBack) {
                    final Request.StringCallBack stringCallBack = (Request.StringCallBack) callback;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            stringCallBack.onOk(content);
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cleanRequest(request);
    }

    private void proxyFailure(final Request request, okhttp3.Request okRequest, final IOException e) {
        w("proxyFailure()-->" + e.toString(), TAG);
        if (needProxy(request)) {
            final Request.Callback callback = request.getCallback();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onError(request, e);
                }
            });
        }
        cleanRequest(request);
    }

    /**
     * 检测当前请求是否还要响应（有没有被cancel）
     *
     * @param request
     * @return
     */
    private boolean needProxy(Request request) {
        return !(CALL_MAP.get(request) == null || CALL_MAP.get(request).isCanceled()) && REQUEST_LIST.contains(request);
    }

    /**
     * 请求结束时调用,清理
     *
     * @param request
     */
    private void cleanRequest(Request request) {
        synchronized (REQUEST_LIST) {
            synchronized (CALL_MAP) {
                CALL_MAP.remove(request);
                REQUEST_LIST.remove(request);
            }
        }
        cleanResponseProgress(request);
    }

    private void cleanResponseProgress(Request request) {
        RESPONSE_PROGRESS_MAP.remove(request);
    }

    @Override
    public void cancel(Request request) {
        if (request == null) {
            w("cancel()-->REQUEST IS NULL!!", TAG);
            return;
        }
        i("cancel()-->request: " + request.toString() + " START", TAG);
        synchronized (REQUEST_LIST) {
            synchronized (CALL_MAP) {
                Call call = CALL_MAP.get(request);
                if (call != null) {
                    call.cancel();
                }
                CALL_MAP.remove(request);
                REQUEST_LIST.remove(request);
                cleanResponseProgress(request);
                d("cancel()-->request: " + request.toString(), TAG);
            }
        }
        i("cancel()-->request: " + request.toString() + " DONE", TAG);
    }

    @Override
    public void cancelTag(Object tag) {
        if (tag == null) {
            w("cancelTag()-->TAG IS NULL!!", TAG);
            return;
        }
        i("cancelTag()-->tag: " + tag.toString() + " START", TAG);
        synchronized (REQUEST_LIST) {
            synchronized (CALL_MAP) {
                Set<Request> keySet = CALL_MAP.keySet();
                for (Request request : keySet) {
                    if (tag.equals(request.getTag())) {
                        Call call = CALL_MAP.get(request);
                        call.cancel();
                        CALL_MAP.remove(request);
                        REQUEST_LIST.remove(request);
                        cleanResponseProgress(request);
                        d("cancelTag()-->tag: " + tag.toString() + " FOUND request: " + request.toString(), TAG);
                    }
                }
            }
        }
        i("cancelTag()-->tag: " + tag.toString() + " DONE", TAG);
    }

    @Override
    public void cancelAll() {
        i("cancelAll()-->START", TAG);
        synchronized (REQUEST_LIST) {
            synchronized (CALL_MAP) {
                Set<Request> keySet = CALL_MAP.keySet();
                for (Request request : keySet) {
                    Call call = CALL_MAP.get(request);
                    call.cancel();
                    CALL_MAP.remove(request);
                    REQUEST_LIST.remove(request);
                    cleanResponseProgress(request);
                    d("cancelAll()-->" + request.toString(), TAG);
                }
            }
        }
        i("cancelAll()-->DONE", TAG);
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(path);
        if (TextUtils.isEmpty(contentType)) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    private boolean compareOkHttp3Request(okhttp3.Request req1, okhttp3.Request req2) {
        if (!req1.toString().equals(req2.toString())) {
            return false;
        }
        RequestBody rb1 = req1.body();
        RequestBody rb2 = req2.body();
        if ((rb1 == null && rb2 != null) || (rb1 != null && rb2 == null)) {
            return false;
        }
        if ((rb1 instanceof FormBody && !(rb2 instanceof FormBody)) || (!(rb1 instanceof FormBody) && rb2 instanceof FormBody)) {
            return false;
        } else if ((rb1 instanceof MultipartBody && !(rb2 instanceof MultipartBody)) || (!(rb1 instanceof MultipartBody) && rb2 instanceof MultipartBody)) {
            return false;
        } else if ((rb1 instanceof ProgressRequestBody && !(rb2 instanceof ProgressRequestBody)) || (!(rb1 instanceof ProgressRequestBody) && rb2 instanceof ProgressRequestBody)) {
            return false;
        }
        if ((rb1.contentType() == null && rb2.contentType() != null) || (rb1.contentType() != null && rb2.contentType() == null) || (!rb1.contentType().equals(rb2.contentType()))) {
            return false;
        }
        try {
            if (rb1.contentLength() != rb2.contentLength()) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void d(String msg, String tag) {
        if (HttpMgr.isDebug()) {
            Log.d(tag, msg);
        }
    }

    private void i(String msg, String tag) {
        if (HttpMgr.isDebug()) {
            Log.i(tag, msg);
        }
    }

    private void w(String msg, String tag) {
        if (HttpMgr.isDebug()) {
            Log.w(tag, msg);
        }
    }
}
