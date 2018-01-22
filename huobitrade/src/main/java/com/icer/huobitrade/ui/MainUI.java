package com.icer.huobitrade.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.icer.huobitrade.R;
import com.icer.huobitrade.app.App;
import com.icer.huobitrade.app.BaseUI;
import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.entity.KLine;
import com.icer.huobitrade.entity.Symbol;
import com.icer.huobitrade.service.MarketService;
import com.icer.huobitrade.util.SpUtil;
import com.icer.huobitrade.util.ToastUtil;
import com.icer.huobitrade.view.Input2Dialog;


public class MainUI extends BaseUI {

    EditText mEtUpAlert;
    EditText mEtDownAlert;
    ToggleButton mTbAlert;
    ToggleButton mTbAutoSell;
    ToggleButton mTbAutoBuy;
    TextView mTvTradeRecords;
    TextView mTvMarketInfo;
    Button mBtnBestBuy;
    Button mBtnBestSell;
    TextView mTvOrderValue;
    SeekBar mSbValue;

    private KLine mTicker;
    private float mBorderUp = -1;
    private float mBorderDown = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {//开始获取最新市场数据
            Intent service = new Intent(getBaseActivity(), MarketService.class);
            service.putExtra(Symbol.class.getSimpleName(), App.getApp().getSymbol());
            startService(service);
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mBorderDown = SpUtil.getFloat(Constants.SP_KEY_BORDER_DOWN, -1);
        mBorderUp = SpUtil.getFloat(Constants.SP_KEY_BORDER_UP, -1);
    }

    @Override
    protected void initView() {
        super.initView();
        updateTitle("");
        mEtDownAlert = findViewById(R.id.et_down);
        mEtUpAlert = findViewById(R.id.et_up);
        mTbAlert = findViewById(R.id.tb_alert);
        {
            if (mBorderUp != -1) {
                mEtUpAlert.setText(mBorderUp + "");
            }
            if (mBorderDown != -1) {
                mEtDownAlert.setText(mBorderDown + "");
            }
            mTbAlert.setChecked(SpUtil.getBoolean(Constants.SP_FLAG_BORDER_ALERT, false));
        }
    }

    private void updateTitle(String price) {
        getSupportActionBar().setTitle(App.getApp().getSymbol().getShowSymbol() + (TextUtils.isEmpty(price) ? "" : ("(" + price + ")")));
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        registerLocalBroadcast(Constants.LB_LOGIN, Constants.LB_NEW_TICKER);
        mEtDownAlert.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mBorderDown = Float.parseFloat(s.toString());
                    SpUtil.setFloat(Constants.SP_KEY_BORDER_DOWN, mBorderDown);
                } catch (Exception e) {
                    e.printStackTrace();
                    mBorderDown = -1;
                } finally {
                    Log.i("border", +mBorderDown + "");
                }
            }
        });
        mEtUpAlert.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    mBorderUp = Float.parseFloat(s.toString());
                    SpUtil.setFloat(Constants.SP_KEY_BORDER_UP, mBorderUp);
                } catch (Exception e) {
                    e.printStackTrace();
                    mBorderUp = -1;
                } finally {
                    Log.i("border", +mBorderUp + "");
                }
            }
        });
        mTbAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = mTbAlert.isChecked();
                SpUtil.setBoolean(Constants.SP_FLAG_BORDER_ALERT, checked);
            }
        });
    }

    @Override
    public void handleLocalBroadcast(Context context, Intent intent) {
        super.handleLocalBroadcast(context, intent);
        String action = intent.getAction();
        switch (action) {
            case Constants.LB_LOGIN: {
                boolean result = intent.getBooleanExtra(Constants.EXTRA_LB_FLAG, true);
                ToastUtil.toastShort(result ? "账户成功登录" : "账户信息有误");
                break;
            }
            case Constants.LB_NEW_TICKER: {
                if (isFinishing() || isDestroyed()) {
                    App.getApp().shutAlert();
                    return;
                }
                KLine ticker = (KLine) intent.getSerializableExtra(Constants.EXTRA_LB_DATA);
                mTicker = ticker;
                if (mTbAlert.isChecked()) {
                    if (mBorderUp != -1 && ticker.getClose() > mBorderUp) {
                        App.getApp().doBorderAlert(true);
                    } else if (mBorderDown != -1 && ticker.getClose() < mBorderDown) {
                        App.getApp().doBorderAlert(false);
                    } else {
                        App.getApp().shutAlert();
                    }
                } else {
                    App.getApp().shutAlert();
                }
                updateTitle(mTicker.getClose() + "");
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(getBaseActivity())
                .setTitle("返回")
                .setMessage("真的要返回么？离开此界面将不会收到价格变动提示等福利哦。")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getBaseContext(), SelectSymbolUI.class));
                        MainUI.super.onBackPressed();
                        mTbAlert.setChecked(false);
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
