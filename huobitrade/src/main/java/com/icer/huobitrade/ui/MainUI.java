package com.icer.huobitrade.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.icer.huobitrade.R;
import com.icer.huobitrade.app.App;
import com.icer.huobitrade.app.BaseUI;
import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.util.ToastUtil;
import com.icer.huobitrade.view.Input2Dialog;


public class MainUI extends BaseUI {
    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportActionBar().setTitle(App.getApp().getSymbol().getShowSymbol());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        registerLocalBroadcast(Constants.LB_LOGIN);
    }

    @Override
    public void handleLocalBroadcast(Context context, Intent intent) {
        super.handleLocalBroadcast(context, intent);
        String action = intent.getAction();
        if (Constants.LB_LOGIN.equals(action)) {
            boolean result = intent.getBooleanExtra(Constants.EXTRA_LB_FLAG, true);
            ToastUtil.toastShort(result ? "账户成功登录" : "账户信息有误");
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(getBaseActivity())
                .setTitle("返回")
                .setMessage("真的要离开么？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainUI.super.onBackPressed();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(1, 1, 0, "登录");
        mi.setIcon(R.mipmap.ic_account);
        mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        MenuItem account = menu.add(2, 2, 0, "账户信息");
        account.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        MenuItem setting = menu.add(2, 3, 0, "设置");
        account.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                Input2Dialog i2d = new Input2Dialog(getBaseActivity());
                i2d.show("输入用户信息", "AppKeyId", "AppSecret", new Input2Dialog.Callback() {
                    @Override
                    public void onOk(String input1, String input2) {
                        App.getApp().setAppKey(input1);
                        App.getApp().setSecret(input2);
                        App.getApp().initAccounts();
                    }
                });
                return true;
            }
            case 2: {
                startActivity(new Intent(getBaseActivity(), AccountUI.class));
                return true;
            }
            case 3: {
                startActivity(new Intent(getBaseActivity(), SettingUI.class));
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
