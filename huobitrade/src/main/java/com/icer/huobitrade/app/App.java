package com.icer.huobitrade.app;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.icer.huobitrade.R;
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
    private boolean mBorderAlert;
    private MediaPlayer mMediaPlayerUp;
    private MediaPlayer mMediaPlayerDown;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        mMediaPlayerUp = MediaPlayer.create(this, R.raw.sound_up);
        mMediaPlayerDown = MediaPlayer.create(this, R.raw.sound_down);
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

    public void shutAlert() {
        if (mBorderAlert) {
            mBorderAlert = false;
            vibrateCancel(this);
        }
    }

    public void doBorderAlert(final boolean isUp) {
        if (mBorderAlert)
            return;
        mBorderAlert = true;
        new Thread() {
            @Override
            public void run() {
                while (mBorderAlert) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (isUp) {
                                mMediaPlayerUp.start();
                            } else {
                                mMediaPlayerDown.start();
                            }
                        }
                    });
                    vibrate(getApp(), isUp ? 750 : 1500);
                    SystemClock.sleep(3000);
                }
            }
        }.start();
    }

    //震动milliseconds毫秒
    public static void vibrate(final Context context, long milliseconds) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    //以pattern[]方式震动
    public static void vibrate(final Context context, long[] pattern, int repeat) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, repeat);
    }

    //取消震动
    public static void vibrateCancel(final Context context) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }
}
