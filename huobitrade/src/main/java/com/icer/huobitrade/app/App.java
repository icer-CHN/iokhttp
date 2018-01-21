package com.icer.huobitrade.app;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.icer.huobitrade.entity.Account;
import com.icer.huobitrade.entity.Symbol;
import com.icer.huobitrade.http.req.ReqAccount;
import com.icer.huobitrade.http.resp.AccountsResp;
import com.icer.huobitrade.util.EncodeUtil;
import com.icer.huobitrade.util.JsonUtil;
import com.icer.huobitrade.util.SpUtil;
import com.icer.iokhttplib.Request;

import java.util.ArrayList;
import java.util.List;


public class App extends Application {
    private static App sApp;

    private List<Account> mAccount = new ArrayList<>();
    private Symbol mSymbol;


    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        if (!TextUtils.isEmpty(getAppkey()) && !TextUtils.isEmpty(getSecret())) {
            initAccounts();
        }
    }

    public static App getApp() {
        return sApp;
    }

    public String getAlertOverSender() {
        String data = SpUtil.getString(Constants.SP_KEY_ALERTOVER_SENDER, "");
        return data;
    }

    public String getAlertOverReceiver() {
        String data = SpUtil.getString(Constants.SP_KEY_ALERTOVER_RECEIVER, "");
        return data;
    }

    public void setAppKey(String data) {
        if (!TextUtils.isEmpty(data)) {
            SpUtil.setString(Constants.SP_KEY_APPKEY, EncodeUtil.base64(data.getBytes()));
        }
    }

    public String getAppkey() {
        String data = SpUtil.getString(Constants.SP_KEY_APPKEY, "");
        if (!TextUtils.isEmpty(data)) {
            data = EncodeUtil.decodeBase64(data);
        }
        return data;
    }

    public void setSecret(String data) {
        if (!TextUtils.isEmpty(data)) {
            SpUtil.setString(Constants.SP_KEY_SECRET, EncodeUtil.base64(data.getBytes()));
        }
    }

    public String getSecret() {
        String data = SpUtil.getString(Constants.SP_KEY_SECRET, "");
        if (!TextUtils.isEmpty(data)) {
            data = EncodeUtil.decodeBase64(data);
        }
        return data;
    }

    public List<Account> getAccount() {
        return mAccount;
    }

    public void setAccount(List<Account> account) {
        mAccount = account;
    }

    public Symbol getSymbol() {
        if (mSymbol == null) {
            String json = SpUtil.getString(Constants.SP_KEY_SYMBOL, "");
            if (!TextUtils.isEmpty(json)) {
                mSymbol = JsonUtil.fromJson(json, Symbol.class);
                Log.i("symbol", "读取币种：" + mSymbol);
            }
        }
        return mSymbol;
    }

    public void setSymbol(Symbol symbol) {
        mSymbol = symbol;
        SpUtil.setString(Constants.SP_KEY_SYMBOL, JsonUtil.toJson(mSymbol));
        Log.i("symbol", "选择币种：" + symbol);
    }

    public void initAccounts() {
        new Thread() {
            @Override
            public void run() {
                ReqAccount.getAccounts(new Request.EntityCallback<AccountsResp>(AccountsResp.class) {
                    @Override
                    public void onEntity(AccountsResp entity) {
                        Log.i("InitAccounts", entity.toString());
                        Intent lb = new Intent(Constants.LB_LOGIN);
                        if (entity.isOk()) {
                            mAccount.addAll(entity.getData());
                            lb.putExtra(Constants.EXTRA_LB_FLAG, true);
                        } else {
                            lb.putExtra(Constants.EXTRA_LB_FLAG, false);
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initAccounts();
                                }
                            }, 1000 * 10);
                        }
                        localBroadcast(lb);
                    }
                });
            }
        }.start();

    }


    public void localBroadcast(Intent intent) {
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
