package com.android.byc.hello.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.android.byc.hello.db.DaoMaster;
import com.android.byc.hello.db.DaoSession;
import com.android.byc.hello.db.MyDatabaseHelper;
import com.facebook.stetho.Stetho;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 15:51
 * @description
 */
public class MyApplication extends Application {

    private static DaoSession daoSession;

    private MyDatabaseHelper dbHelper;
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        dbHelper = new MyDatabaseHelper(this, "HouseCoins.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DaoMaster.createAllTables(db, true);
        DaoMaster daoMaster = new DaoMaster(db) ;
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
