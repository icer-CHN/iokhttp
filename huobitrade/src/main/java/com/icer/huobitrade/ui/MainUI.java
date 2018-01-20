package com.icer.huobitrade.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.icer.huobitrade.R;
import com.icer.huobitrade.app.App;
import com.icer.huobitrade.app.BaseUI;
import com.icer.huobitrade.entity.Account;
import com.icer.huobitrade.http.req.ReqAccount;
import com.icer.huobitrade.http.req.ReqCommon;
import com.icer.huobitrade.http.req.ReqMarket;
import com.icer.huobitrade.http.resp.BalanceResp;
import com.icer.huobitrade.http.resp.AccountsResp;
import com.icer.huobitrade.http.resp.CurrencysResp;
import com.icer.huobitrade.http.resp.DepthResp;
import com.icer.huobitrade.http.resp.KLineResp;
import com.icer.huobitrade.http.resp.MarketResp;
import com.icer.huobitrade.http.resp.SymbolsResp;
import com.icer.huobitrade.http.resp.TickerResp;
import com.icer.huobitrade.http.resp.TimeStampResp;
import com.icer.huobitrade.http.resp.TradeHistoryResp;
import com.icer.huobitrade.http.resp.TradeResp;
import com.icer.iokhttplib.Request;

public class MainUI extends BaseUI implements View.OnClickListener {

    Button mBtnKline;
    Button mBtnTicker;
    Button mBtnDepth;
    Button mBtnTrade;
    Button mBtnTradeHistory;
    Button mBtnMarket;
    Button mBtnSymbols;

    Button mBtnCurrencys;
    Button mBtnTimestamp;

    Button mBtnAccounts;
    Button mBtnBalance;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        App.getApp().setSymbol("eosusdt");
    }

    @Override
    protected void initView() {
        super.initView();
        mBtnKline = findViewById(R.id.btn_kline);
        mBtnTicker = findViewById(R.id.btn_ticker);
        mBtnDepth = findViewById(R.id.btn_depth);
        mBtnTrade = findViewById(R.id.btn_trade);
        mBtnTradeHistory = findViewById(R.id.btn_trade_history);
        mBtnMarket = findViewById(R.id.btn_market);
        mBtnSymbols = findViewById(R.id.btn_symbols);
        mBtnCurrencys = findViewById(R.id.btn_currencys);
        mBtnTimestamp = findViewById(R.id.btn_timestamp);
        mBtnAccounts = findViewById(R.id.btn_accounts);
        mBtnBalance = findViewById(R.id.btn_balance);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mBtnKline.setOnClickListener(this);
        mBtnTicker.setOnClickListener(this);
        mBtnDepth.setOnClickListener(this);
        mBtnTrade.setOnClickListener(this);
        mBtnTradeHistory.setOnClickListener(this);
        mBtnMarket.setOnClickListener(this);
        mBtnSymbols.setOnClickListener(this);
        mBtnCurrencys.setOnClickListener(this);
        mBtnTimestamp.setOnClickListener(this);
        mBtnAccounts.setOnClickListener(this);
        mBtnBalance.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_kline: {
                ReqMarket.getKLine(App.getApp().getSymbol(), ReqMarket.Period.MIN, 150, new Request.EntityCallback<KLineResp>(KLineResp.class) {
                    @Override
                    public void onEntity(KLineResp entity) {
                        Log.i("KLine", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_ticker: {
                ReqMarket.getTickerDetail(App.getApp().getSymbol(), new Request.EntityCallback<TickerResp>(TickerResp.class) {
                    @Override
                    public void onEntity(TickerResp entity) {
                        Log.i("Ticker", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_depth: {
                ReqMarket.getDepth(App.getApp().getSymbol(), ReqMarket.DepthType.STEP0, new Request.EntityCallback<DepthResp>(DepthResp.class) {
                    @Override
                    public void onEntity(DepthResp entity) {
                        Log.i("Depth", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_trade: {
                ReqMarket.getTrade(App.getApp().getSymbol(), new Request.EntityCallback<TradeResp>(TradeResp.class) {
                    @Override
                    public void onEntity(TradeResp entity) {
                        Log.i("Trade", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_trade_history: {
                ReqMarket.getTradeHistory(App.getApp().getSymbol(), new Request.EntityCallback<TradeHistoryResp>(TradeHistoryResp.class) {
                    @Override
                    public void onEntity(TradeHistoryResp entity) {
                        Log.i("TradeHistory", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_market: {
                ReqMarket.getMarket24H(App.getApp().getSymbol(), new Request.EntityCallback<MarketResp>(MarketResp.class) {
                    @Override
                    public void onEntity(MarketResp entity) {
                        Log.i("Market", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_symbols: {
                ReqCommon.getSymbols(new Request.EntityCallback<SymbolsResp>(SymbolsResp.class) {
                    @Override
                    public void onEntity(SymbolsResp entity) {
                        Log.i("Symbols", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_currencys: {
                ReqCommon.getCurrencys(new Request.EntityCallback<CurrencysResp>(CurrencysResp.class) {
                    @Override
                    public void onEntity(CurrencysResp entity) {
                        Log.i("Currencys", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_timestamp: {
                ReqCommon.getTimestamp(new Request.EntityCallback<TimeStampResp>(TimeStampResp.class) {
                    @Override
                    public void onEntity(TimeStampResp entity) {
                        Log.i("Timestamp", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_accounts: {
                ReqAccount.getAccounts(new Request.EntityCallback<AccountsResp>(AccountsResp.class) {
                    @Override
                    public void onEntity(AccountsResp entity) {
                        Log.i("Accounts", entity.toString());

                    }
                });
            }
            break;
            case R.id.btn_balance: {
                if (App.getApp().getAccount().size() == 0)
                    return;
                for (Account account : App.getApp().getAccount()) {
                    ReqAccount.getBalance(account.getId() + "", new Request.EntityCallback<BalanceResp>(BalanceResp.class) {
                        @Override
                        public void onEntity(BalanceResp entity) {
                            Log.i("Balance", entity.toString());
                        }
                    });
                }
            }
            break;
        }
    }
}
