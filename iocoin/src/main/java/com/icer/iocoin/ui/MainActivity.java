package com.icer.iocoin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.icer.iocoin.Constants;
import com.icer.iocoin.R;
import com.icer.iocoin.entity.CoinInfo;
import com.icer.iocoin.util.GsonUtil;
import com.icer.iokhttplib.HttpMgr;
import com.icer.iokhttplib.Request;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSrl;
    private ListViewCompat mLv;

    private BaseAdapter mAdapter;
    private List<CoinInfo> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            mSrl = findViewById(R.id.srl);
            mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getData();
                }
            });
        }
        {
            mLv = findViewById(R.id.lv);
            mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CoinInfo ci = mData.get(position);
                    Intent i = new Intent(MainActivity.this, CoinInfoActivity.class);
                    i.putExtra(CoinInfoActivity.EXTRA_KEY_COIN, ci);
                    startActivity(i);
                }
            });
            mAdapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return mData.size();
                }

                @Override
                public Object getItem(int position) {
                    return mData.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_coins, parent, false);
                    }
                    TextView tvName = convertView.findViewById(R.id.tv_name);
                    TextView tvPrice = convertView.findViewById(R.id.tv_price);
                    TextView tvChange = convertView.findViewById(R.id.tv_change);
                    TextView tvChangePer = convertView.findViewById(R.id.tv_change_percentage);

                    tvPrice.setTextColor(getResources().getColor(R.color.text));
                    tvChange.setVisibility(View.GONE);
                    tvChangePer.setVisibility(View.GONE);

                    CoinInfo ci = mData.get(position);
                    tvName.setText(ci.getSymbol());
                    tvPrice.setText(getString(R.string.cny) + ci.getPriceCny());
                    return convertView;
                }
            };
            mLv.setAdapter(mAdapter);
        }
        mSrl.setRefreshing(true);
        getData();
    }

    private void getData() {
        HttpMgr.get(new Request.Builder()
                .url(Constants.URL + "?" + "limit=100" + "&" + "convert=CNY")
                .callback(new Request.StringCallBack() {
                    @Override
                    public void onOk(String content) {
                        super.onOk(content);
                        mSrl.setRefreshing(false);
                        try {
                            JSONArray ja = new JSONArray(content);
                            mData.clear();
                            for (int i = 0; i < ja.length(); i++) {
                                String obj = ja.getString(i);
                                mData.add(GsonUtil.json2Entity(obj, CoinInfo.class));
                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        mSrl.setRefreshing(false);
                    }
                })
                .build());
    }
}
