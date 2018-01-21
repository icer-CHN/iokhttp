package com.icer.huobitrade.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.icer.huobitrade.R;
import com.icer.huobitrade.app.Constants;
import com.icer.huobitrade.entity.Symbol;
import com.icer.huobitrade.entity.Ticker;
import com.icer.huobitrade.http.req.ReqMarket;
import com.icer.huobitrade.http.resp.TickerResp;
import com.icer.huobitrade.ui.MainUI;
import com.icer.iokhttplib.Request;


public class MarketService extends Service {

    public static final String TAG = MarketService.class.getSimpleName();

    private Symbol mSymbol;
    private boolean mInTask;//是否在定时请求数据的任务中
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand()-->" + intent);
        Object object = intent.getSerializableExtra(Symbol.class.getSimpleName());
        Symbol symbol = null;
        if (object != null && object instanceof Symbol) {
            symbol = (Symbol) object;
        }
        if (symbol != null) {
            if (mLocalBroadcastManager == null) {
                mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
            }
            mSymbol = symbol;
            mInTask = true;
            new Thread() {
                @Override
                public void run() {
                    while (mInTask) {
                        SystemClock.sleep(200);
                        ReqMarket.getTickerDetail(mSymbol.getSymbol(), new Request.EntityCallback<TickerResp>(TickerResp.class) {
                            @Override
                            public void onEntity(final TickerResp entity) {
                                if (entity.isOk()) {
                                    Intent i = new Intent(Constants.LB_NEW_TICKER);
                                    i.putExtra(Constants.EXTRA_LB_DATA, entity.getTick());
                                    mLocalBroadcastManager.sendBroadcast(i);
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            updateNotification(entity.getTick());
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }.start();

        }
        return super.onStartCommand(intent, flags, startId);
    }

    private NotificationManager mNotificationManager;

    private void updateNotification(Ticker ticker) {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(this, MainUI.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .setAutoCancel(false)
                .setContentTitle(mSymbol.getShowSymbol().toUpperCase())
                .setContentText(ticker.getClose() + "")
                .setSubText(ticker.getResult().name())
                .setOngoing(true);
        startForeground(120, builder.build());
    }

    @Override
    public boolean stopService(Intent name) {
        Log.i(TAG, "stopService()-->" + name);
        mInTask = false;
        return super.stopService(name);
    }
}
