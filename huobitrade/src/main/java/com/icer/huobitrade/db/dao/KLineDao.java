package com.icer.huobitrade.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.icer.huobitrade.entity.KLine;

import java.util.List;

@Dao
public interface KLineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(KLine kLine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<KLine> kLines);

    @Delete
    void delete(KLine kLine);

    @Delete
    void deleteAll(List<KLine> kLines);

    @Update
    void update(KLine kLine);

    @Update
    void updateAll(List<KLine> kLines);

    @Query("SELECT * FROM KLine WHERE symbol = :symbol AND period = :period AND id = :id")
    KLine queryBySymbolPeriodId(String symbol, String period, long id);

    @Query("SELECT * FROM KLine WHERE symbol = :symbol AND period = :period ORDER BY _id DESC LIMIT :size")
    List<KLine> queryBySymbolAndPeriod(String symbol, String period, int size);
}
