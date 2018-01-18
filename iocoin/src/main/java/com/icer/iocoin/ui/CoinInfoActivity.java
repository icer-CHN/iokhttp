package com.icer.iocoin.ui;

import android.app.Activity;
import android.app.Service;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.icer.iocoin.App;
import com.icer.iocoin.Constants;
import com.icer.iocoin.R;
import com.icer.iocoin.entity.CoinInfo;
import com.icer.iocoin.util.GsonUtil;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.Executors;

public class CoinInfoActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_COIN = "EXTRA_KEY_COIN";

    private SwipeRefreshLayout mSrl;
    private TextView mTvName;
    private TextView mTvPriceUsd;
    private TextView mTvPrice;
    private TextView mTvChange;
    private TextView mTvChangePer;
    private Button mBtnAlertStop;
    private EditText mEtBorderUp;
    private EditText mEtBorderDown;
    private boolean mBorderAlert;
    private double mBorderUp = -1;
    private double mBorderDown = -1;

    private CoinInfo mCoinInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCoinInfo = (CoinInfo) getIntent().getSerializableExtra(EXTRA_KEY_COIN);
        setContentView(R.layout.activity_coin_info);
        {
            mSrl = findViewById(R.id.srl);
            mTvName = findViewById(R.id.tv_name);
            mTvPriceUsd = findViewById(R.id.tv_price_usd);
            mTvPrice = findViewById(R.id.tv_price);
            mTvChange = findViewById(R.id.tv_change);
            mTvChangePer = findViewById(R.id.tv_change_percentage);
            mBtnAlertStop = findViewById(R.id.btn_alert_stop);
            {
                mBtnAlertStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBtnAlertStop.setVisibility(View.GONE);
                        mBorderAlert = false;
                        vibrateCancel(CoinInfoActivity.this);
                    }
                });
            }
            mEtBorderUp = findViewById(R.id.et_up_border);
            mEtBorderDown = findViewById(R.id.et_down_border);
            {
                mEtBorderUp.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            mBorderUp = Double.parseDouble(s.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            mBorderUp = -1;
                        } finally {
                            Log.i("border", +mBorderUp + "");
                        }
                    }
                });
                mEtBorderDown.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            mBorderDown = Double.parseDouble(s.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            mBorderDown = -1;
                        } finally {
                            Log.i("border", +mBorderDown + "");
                        }
                    }
                });
            }

            mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getData();
                }
            });

            updateData(mCoinInfo);
        }
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... strings) {
                while (!isFinishing() && !isDestroyed()) {
                    SystemClock.sleep(1000 * 30);
                    getData();
                }
                return null;
            }
        }.executeOnExecutor(Executors.newCachedThreadPool());
    }

    @Override
    public void finish() {
        vibrateCancel(this);
        mBorderAlert = false;
        super.finish();
    }

    private void updateData(CoinInfo coinInfo) {
        if (coinInfo.isLatestThan(mCoinInfo)) {
            doCmp(coinInfo, mCoinInfo);
            mCoinInfo = coinInfo;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvName.setText(mCoinInfo.getSymbol());
                mTvPriceUsd.setText(getString(R.string.usd) + mCoinInfo.getPriceUsd());
                mTvPrice.setText(getString(R.string.cny) + mCoinInfo.getPriceCny());
            }
        });
    }


    private void doCmp(CoinInfo latest, CoinInfo last) {
        double priceNewUsd = Double.parseDouble(latest.getPriceUsd());
        double priceOldUsd = Double.parseDouble(last.getPriceUsd());
        double dPrice = Math.abs(priceNewUsd - priceOldUsd);
        double changePer = dPrice / priceOldUsd * 100;
        boolean isUp = priceNewUsd > priceOldUsd;
        {
            if (mBorderUp != -1 && priceNewUsd >= mBorderUp) {
                doBorderAlert(true);
            } else if (mBorderDown != -1 && priceNewUsd <= mBorderDown) {
                doBorderAlert(false);
            }
            if (isUp) {
                App.getInstance().mMediaPlayerUp.start();
            } else {
                App.getInstance().mMediaPlayerDown.start();
            }
        }
        int color = getResources().getColor(isUp ? R.color.RED : R.color.GREEN);
        mTvPriceUsd.setTextColor(color);
        mTvPrice.setTextColor(color);
        mTvChange.setTextColor(color);
        mTvChangePer.setTextColor(color);
        mTvChange.setText((isUp ? "+" : "-") + getString(R.string.usd) + String.format("%.2f", dPrice));
        mTvChangePer.setText("%" + String.format("%.3f", changePer));
    }

    private Handler mHandler = new Handler();

    private void doBorderAlert(final boolean isUp) {
        if (mBorderAlert)
            return;
        mBorderAlert = true;
        mBtnAlertStop.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                while (mBorderAlert) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (isUp) {
                                App.getInstance().mMediaPlayerUp.start();
                            } else {
                                App.getInstance().mMediaPlayerDown.start();
                            }
                        }
                    });
                    vibrate(CoinInfoActivity.this, 2000);
                    SystemClock.sleep(3000);
                }
            }
        }.start();
    }

    private boolean mIsRequesting;

    private void getData() {
        if (mIsRequesting)
            return;
        mIsRequesting = true;
        HttpMgr.get(new Request.Builder()
                .url(Constants.URL + mCoinInfo.getId() + "/" + "?" + "convert=CNY")
                .callback(new Request.StringCallBack() {
                    @Override
                    public void onOk(String content) {
                        super.onOk(content);
                        mIsRequesting = false;
                        try {
                            JSONArray ja = new JSONArray(content);
                            CoinInfo ci = null;
                            for (int i = 0; i < ja.length(); i++) {
                                String obj = ja.getString(i);
                                ci = GsonUtil.json2Entity(obj, CoinInfo.class);
                            }
                            if (ci != null)
                                updateData(ci);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSrl.setRefreshing(false);
                            }
                        });
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        mIsRequesting = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mSrl.setRefreshing(false);
                            }
                        });
                    }
                })
                .build());
    }

    //震动milliseconds毫秒
    public static void vibrate(final Activity activity, long milliseconds) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(milliseconds);
    }

    //以pattern[]方式震动
    public static void vibrate(final Activity activity, long[] pattern, int repeat) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, repeat);
    }

    //取消震动
    public static void vibrateCancel(final Activity activity) {
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }

}
