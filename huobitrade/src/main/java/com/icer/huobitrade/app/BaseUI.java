package com.icer.huobitrade.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseUI extends AppCompatActivity {
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataPreSetContentView();
        setContentView(bindLayout());
        initData();
        initView();
        initEvent();
        doBusiness();
    }

    protected abstract int bindLayout();

    protected void initDataPreSetContentView() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected void initEvent() {

    }

    protected void doBusiness() {

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void handleLocalBroadcast(Context context, Intent intent) {

    }

    public void registerLocalBroadcast(String... actions) {
        if (actions == null || actions.length == 0)
            return;
        IntentFilter intentFilter = new IntentFilter();
        for (String act : actions) {
            intentFilter.addAction(act);
        }
        if (mReceiver == null) {
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    handleLocalBroadcast(context, intent);
                }
            };
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }

    public BaseUI getBaseActivity() {
        return BaseUI.this;
    }

    public final String getBaseTag() {
        return getClass().getSimpleName();
    }
}
