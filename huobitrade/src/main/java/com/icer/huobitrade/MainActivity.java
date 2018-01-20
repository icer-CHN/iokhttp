package com.icer.huobitrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        HttpMgr.post(new AppRequestBuilder(Constants.PATH_TRADE,API.COMMON_CURRENCYS)
                .callback(new Request.StringCallBack() {
                    @Override
                    public void onOk(String content) {
                        super.onOk(content);
                        Log.i("K_LINE",content);
                    }
                })
                .build());
    }
}
