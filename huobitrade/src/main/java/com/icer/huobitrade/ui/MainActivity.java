package com.icer.huobitrade.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.icer.huobitrade.R;
import com.icer.huobitrade.http.API;
import com.icer.huobitrade.http.AppRequestBuilder;
import com.icer.huobitrade.http.resp.BaseResp;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        HttpMgr.get(new AppRequestBuilder(true, API.M_K_LINE)
                .callback(new Request.EntityCallback<BaseResp>(BaseResp.class) {
                    @Override
                    public void onEntity(BaseResp entity) {
                        if(entity.isOk()){

                        }
                    }
                })
                .build());
    }
}
