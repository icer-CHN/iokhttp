package com.icer.huobitrade.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.icer.huobitrade.R;
import com.icer.huobitrade.app.App;
import com.icer.huobitrade.app.BaseUI;
import com.icer.huobitrade.entity.Symbol;
import com.icer.huobitrade.http.req.ReqCommon;
import com.icer.huobitrade.http.resp.SymbolsResp;
import com.icer.iokhttplib.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelectSymbolUI extends BaseUI {
    private SwipeRefreshLayout mSrl;
    private ListViewCompat mLv;

    private BaseAdapter mAdapter;
    private List<Symbol> mData = new ArrayList<>();
    private Symbol mLastSymbol;

    @Override
    protected int bindLayout() {
        return R.layout.activity_select_symbol;
    }

    @Override
    protected void initDataPreSetContentView() {
        super.initDataPreSetContentView();
        mLastSymbol = App.getApp().getSymbol();
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportActionBar().setTitle("选择币种");
        mSrl = findViewById(R.id.srl);
        mLv = findViewById(R.id.lv);
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
                    convertView = LayoutInflater.from(SelectSymbolUI.this).inflate(R.layout.item_coins, parent, false);
                }
                TextView tvName = convertView.findViewById(R.id.tv_name);
                TextView tvPartition = convertView.findViewById(R.id.tv_partition);

                Symbol ci = mData.get(position);
                tvName.setText(ci.getShowSymbol());
                tvPartition.setText(ci.getPartition());
                return convertView;
            }
        };
        mLv.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Symbol ci = mData.get(position);
                App.getApp().setSymbol(ci);
                goNextPage();
            }
        });
    }

    @Override
    protected void doBusiness() {
        super.doBusiness();
        mSrl.setRefreshing(true);
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mLastSymbol != null) {
            MenuItem mi = menu.add(1, 1, 0, mLastSymbol.getShowSymbol());
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return (mLastSymbol != null) || super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            goNextPage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(getBaseActivity())
                .setTitle("退出")
                .setMessage("真的要退出APP么？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectSymbolUI.super.onBackPressed();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void getData() {
        ReqCommon.getSymbols(new Request.EntityCallback<SymbolsResp>(SymbolsResp.class) {
            Comparator<Symbol> c = new Comparator<Symbol>() {
                @Override
                public int compare(Symbol o1, Symbol o2) {
                    return o1.getBaseCurrency().compareTo(o2.getBaseCurrency());
                }
            };

            @Override
            public void onEntity(SymbolsResp entity) {
                if (entity.isOk()) {
                    mData.clear();
                    mData.addAll(entity.getData());
                    Collections.sort(mData, c);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFinish() {
                mSrl.setRefreshing(false);
            }
        });
    }

    private void goNextPage() {
        Intent i = new Intent(getBaseActivity(), MainUI.class);
        startActivity(i);
    }
}
