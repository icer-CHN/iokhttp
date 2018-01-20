package com.icer.huobitrade.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cljlo on 2018/1/20.
 */

public abstract class BaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataPreSetContentView();
        setContentView(bindLayout());
        initData();
        initView();
        initEvent();
        doBuiness();
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

    protected void doBuiness() {

    }
}
