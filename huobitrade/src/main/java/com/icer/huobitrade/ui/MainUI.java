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
import com.icer.huobitrade.http.req.ReqOrder;
import com.icer.huobitrade.http.resp.AccountsResp;
import com.icer.huobitrade.http.resp.BalanceResp;
import com.icer.huobitrade.http.resp.BatchCancelResp;
import com.icer.huobitrade.http.resp.CurrencysResp;
import com.icer.huobitrade.http.resp.DepthResp;
import com.icer.huobitrade.http.resp.KLineResp;
import com.icer.huobitrade.http.resp.LongResp;
import com.icer.huobitrade.http.resp.MarketResp;
import com.icer.huobitrade.http.resp.MatchsResp;
import com.icer.huobitrade.http.resp.OrderDetailResp;
import com.icer.huobitrade.http.resp.OrderMatchResp;
import com.icer.huobitrade.http.resp.OrdersResp;
import com.icer.huobitrade.http.resp.StringResp;
import com.icer.huobitrade.http.resp.SymbolsResp;
import com.icer.huobitrade.http.resp.TickerResp;
import com.icer.huobitrade.http.resp.TradeHistoryResp;
import com.icer.huobitrade.http.resp.TradeResp;
import com.icer.iokhttplib.Request;

import java.util.ArrayList;
import java.util.List;

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

    Button mBtnOrderPlace;
    Button mBtnOrders;
    Button mBtnMatchs;
    Button mBtnOrderCancel;
    Button mBtnOrderBatchCancel;
    Button mBtnOrderDetail;
    Button mBtnOrderMatch;

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
        mBtnOrderPlace = findViewById(R.id.btn_order_place);
        mBtnOrders = findViewById(R.id.btn_orders);
        mBtnMatchs = findViewById(R.id.btn_matchs);
        mBtnOrderCancel = findViewById(R.id.btn_order_cancel);
        mBtnOrderBatchCancel = findViewById(R.id.btn_order_batch_cancel);
        mBtnOrderDetail = findViewById(R.id.btn_order_detail);
        mBtnOrderMatch = findViewById(R.id.btn_order_match);
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
        mBtnOrderPlace.setOnClickListener(this);
        mBtnOrders.setOnClickListener(this);
        mBtnMatchs.setOnClickListener(this);
        mBtnOrderCancel.setOnClickListener(this);
        mBtnOrderBatchCancel.setOnClickListener(this);
        mBtnOrderDetail.setOnClickListener(this);
        mBtnOrderMatch.setOnClickListener(this);
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
                ReqCommon.getTimestamp(new Request.EntityCallback<LongResp>(LongResp.class) {
                    @Override
                    public void onEntity(LongResp entity) {
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
            case R.id.btn_order_place: {
                if (App.getApp().getAccount().size() == 0)
                    return;
                for (Account account : App.getApp().getAccount()) {
                    if (account.isMarginAccount()) {
                        ReqOrder.place(account.getId() + "", "1", "1", true,
                                App.getApp().getSymbol(), ReqOrder.OrderType.BUY_LIMIT,
                                new Request.EntityCallback<StringResp>(StringResp.class) {
                                    @Override
                                    public void onEntity(StringResp entity) {
                                        Log.i("Order", entity.toString());
                                    }
                                });
                    }
                }
            }
            break;
            case R.id.btn_orders: {
                ReqOrder.orders(App.getApp().getSymbol(), ReqOrder.OrderState.FILLED,
                        new Request.EntityCallback<OrdersResp>(OrdersResp.class) {
                            @Override
                            public void onEntity(OrdersResp entity) {
                                Log.i("Orders", entity.toString());
                            }
                        });
            }
            break;
            case R.id.btn_matchs: {
                ReqOrder.matchResults(App.getApp().getSymbol(),
                        new Request.EntityCallback<MatchsResp>(MatchsResp.class) {
                            @Override
                            public void onEntity(MatchsResp entity) {
                                Log.i("Matchs", entity.toString());
                            }
                        });
            }
            break;
            case R.id.btn_order_cancel: {
                ReqOrder.cancel("80664211", new Request.EntityCallback<StringResp>(StringResp.class) {
                    @Override
                    public void onEntity(StringResp entity) {
                        Log.i("OrderCancel", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_order_batch_cancel: {
                List<String> ids = new ArrayList<>();
                ids.add("80664211");
                ids.add("80664214");
                ReqOrder.cancelBatch(ids, new Request.EntityCallback<BatchCancelResp>(BatchCancelResp.class) {
                    @Override
                    public void onEntity(BatchCancelResp entity) {
                        Log.i("BatchCancel", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_order_detail: {
                ReqOrder.orderDetail("80664211", new Request.EntityCallback<OrderDetailResp>(OrderDetailResp.class) {
                    @Override
                    public void onEntity(OrderDetailResp entity) {
                        Log.i("OrderDetail", entity.toString());
                    }
                });
            }
            break;
            case R.id.btn_order_match: {
                ReqOrder.orderMatch("80664211", new Request.EntityCallback<OrderMatchResp>(OrderMatchResp.class) {
                    @Override
                    public void onEntity(OrderMatchResp entity) {
                        Log.i("OrderMatch", entity.toString());
                    }
                });
            }
            break;
        }
    }
}
