package com.icer.huobitrade.db;


import android.arch.persistence.room.Room;

import com.icer.huobitrade.app.App;

public class DBMgr {

    private static DB sDB;

    public static DB getDB() {
        if (sDB == null) {
            sDB = Room.databaseBuilder(App.getApp(), DB.class, App.getApp().getPackageName()).build();
        }
        return sDB;
    }
}
