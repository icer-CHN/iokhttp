package com.icer.huobitrade.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.icer.huobitrade.db.dao.KLineDao;
import com.icer.huobitrade.entity.KLine;

@Database(version = 1, exportSchema = false, entities = {KLine.class})
public abstract class DB extends RoomDatabase {
    public abstract KLineDao kLineDao();
}
