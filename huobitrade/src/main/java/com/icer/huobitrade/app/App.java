package com.icer.huobitrade.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.icer.huobitrade.entity.Account;
import com.icer.huobitrade.http.req.ReqAccount;
import com.icer.huobitrade.http.resp.AccountsResp;
import com.icer.iokhttplib.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cljlo on 2018/1/20.
 */

public class App extends Application {
    private static App sApp;

    private List<Account> mAccount = new ArrayList<>();
    private String mSymbol;


    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initAccounts();
    }

    public static App getApp() {
        return sApp;
    }

    public List<Account> getAccount() {
        return mAccount;
    }

    public void setAccount(List<Account> account) {
        mAccount = account;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public void initAccounts() {
        new Thread() {
            @Override
            public void run() {
                ReqAccount.getAccounts(new Request.EntityCallback<AccountsResp>(AccountsResp.class) {
                    @Override
                    public void onEntity(AccountsResp entity) {
                        Log.i("InitAccounts", entity.toString());
                        if (entity.isOk()) {
                            mAccount.addAll(entity.getData());
                        } else {
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initAccounts();
                                }
                            }, 1000 * 10);
                        }
                    }
                });
            }
        }.start();

    }

}
