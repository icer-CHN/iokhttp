package com.icer.huobitrade.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.icer.huobitrade.entity.KLine;

import java.util.List;

@Dao
public interface KLineDao {
    @Query("select * from KLine where symbol = :symbol and period = :period")
    List<KLine> getAllBySymbolAndPeriod(String symbol, String period);

    void insertAll(List<KLine> kLines);
}
