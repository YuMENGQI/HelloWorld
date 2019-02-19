package com.android.byc.hello.network;

import android.database.Cursor;

import com.android.byc.hello.db.SQLiteDataProxyForGreenDao;
import com.android.byc.hello.presenter.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 13:45
 * @description
 */
public interface ISQLiteOperate {
    boolean execSQL(String sql);

    boolean execSQLs(String[] sqls);

    boolean execSQLList(List<String> sqlList);

    boolean execSQLs(List<String[]> sqlList);

    Cursor query(String sql);

    Cursor query(String sql, String[] params);

    void doQuery(SQLiteDataProxyForGreenDao.ISQLQuery isqlQuery, String sql);

    <T> T getEntityByKey(String tableName, UUID pk);

    <T> long insert(T entity);

    <T> boolean insertOrReplace(T entity);

    <T> void insertOrReplace(List<T> entityList);

    <T> void delete(T entity);

    <T> void update(T entity);

    void runInTx(Runnable runnable);

    <T> QueryBuilder<T> queryBuilder(Class<T> entityClass);

    <T> List<T> queryRaw(Class<T> entityClass, String conditions);

    DaoSession getDaoSession();
}
