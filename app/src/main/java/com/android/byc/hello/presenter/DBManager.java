package com.android.byc.hello.presenter;


import android.content.Context;
import android.database.Cursor;

import com.android.byc.hello.db.SQLiteDataProxyForGreenDao;
import com.android.byc.hello.network.ISQLiteOperate;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.UUID;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:13
 * @description
 */
public class DBManager {


    private Context context;
    private ISQLiteOperate sqLiteOperate;

    /**
     * 不要在application的生命周期中初始化 DBManager。
     *
     * @param context
     */
    public DBManager(Context context) {
        this.context = context.getApplicationContext();
//		sqLiteOperate = SQLiteDataProxy.getInstance(context);
        sqLiteOperate = SQLiteDataProxyForGreenDao.getInstance();
    }

    public DBManager() {
        //this.context = AppLike.gInstance;
        sqLiteOperate = SQLiteDataProxyForGreenDao.getInstance();
    }


    public void asyncExecSQLs(final List<String[]> sqlList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sqLiteOperate.execSQLs(sqlList);
            }
        }).start();
    }

    public boolean execSQL(String sql) {
        return sqLiteOperate.execSQL(sql);
    }

    public boolean execSQLList(List<String> sqlList) {
        return sqLiteOperate.execSQLList(sqlList);
    }

    /**
     * 这个接口
     * 不要瞎瘠薄调用
     */
    public boolean execSQLs(List<String[]> sqlList) {
        return sqLiteOperate.execSQLs(sqlList);
    }

    /**
     * 同上
     * 能不用就不用
     */
    public boolean execSQLs(String[] sqls) {
        return sqLiteOperate.execSQLs(sqls);
    }

    /**
     * 每调用一次都需要手动close一次
     *
     * @param sql
     * @return
     */
    public Cursor query(String sql) {
        return sqLiteOperate.query(sql);
    }

    /**
     * 每调用一次都需要手动close一次
     *
     * @param sql
     * @param params
     * @return
     */
    public Cursor query(String sql, String[] params) {
        return sqLiteOperate.query(sql, params);
    }

    public DBManager doQuery(String sql, SQLiteDataProxyForGreenDao.ISQLQuery iSqlQuery) {
        sqLiteOperate.doQuery(iSqlQuery, sql);
        return this;
    }

    @Nullable
    public <T> T getEntityByKey(String tableName, UUID pk) {
        return sqLiteOperate.getEntityByKey(tableName, pk);
    }

    public <T> long insert(T entity) {
        return sqLiteOperate.insert(entity);
    }

    public void runInTx(Runnable runnable) {
        sqLiteOperate.runInTx(runnable);
    }

    public <T> boolean insertOrReplace(T entity) {
        return sqLiteOperate.insertOrReplace(entity);
    }

    public <T> void insertOrReplace(List<T> entityList) {
        sqLiteOperate.insertOrReplace(entityList);
    }

    public <T> void delete(T entity) {
        sqLiteOperate.delete(entity);
    }

    public <T> void update(T entity) {
        sqLiteOperate.update(entity);
    }

    public <T> QueryBuilder<T> queryBuilder(Class<T> entityClass) {
        return sqLiteOperate.queryBuilder(entityClass);
    }

    /**
     * 相当于Select * from "entityClass" + "....conditions...."
     *
     * @param entityClass
     * @param conditions
     * @param <T>
     * @return
     */
    @NonNull
    public <T> List<T> queryRaw(Class<T> entityClass, String conditions) {
        return sqLiteOperate.queryRaw(entityClass, conditions);
    }

    public DaoSession getDaoSession() {
        return sqLiteOperate.getDaoSession();
    }
    /**
    public void insertNewChanges(String tableName, UUID pkObject) {
        long syncTime = 999999999999950000L;
        if (ChangeModel.syncTimeForTableName.containsKey(tableName))
            syncTime = ChangeModel.syncTimeForTableName.get(tableName);
        int hasFile = 0;
        if (tableName.equalsIgnoreCase("HouseImages")) {
            hasFile = 1;
        }
        ChangesEntity changesEntity = new ChangesEntity();
        changesEntity.PKChange = pkObject;
        changesEntity.TableName = tableName;
        changesEntity.PKObject = pkObject;
        changesEntity.SyncTime = syncTime;
        changesEntity.SyncType = 0;
        changesEntity.Status = 0;
        changesEntity.ScopeType = 2;
        changesEntity.PKCity = AppLike.PKCity;
        changesEntity.PKCompany = AppLike.PKCompany;
        changesEntity.PKUser = AppLike.PKUser;
        changesEntity.HasFile = hasFile;
        changesEntity.BatchParameter = "";
        sqLiteOperate.insert(changesEntity);
        SyncForwardTask.executeUnlessRunning(context, null);
    }


    public void insertChanges(String tableName, final UUID pkObject, long syncTime) {
        if (ChangeModel.syncTimeForTableName.containsKey(tableName))
            syncTime = ChangeModel.syncTimeForTableName.get(tableName);
        int hasFile = 0;
        if (tableName.equalsIgnoreCase("HouseImages")) {
            hasFile = 1;
        }
        final ChangesEntity changesEntity = new ChangesEntity();
        changesEntity.PKChange = pkObject;
        changesEntity.TableName = tableName;
        changesEntity.PKObject = pkObject;
        changesEntity.SyncTime = syncTime;
        changesEntity.SyncType = 0;
        changesEntity.Status = 0;
        changesEntity.ScopeType = 2;
        changesEntity.PKCity = AppLike.PKCity;
        changesEntity.PKCompany = AppLike.PKCompany;
        changesEntity.PKUser = AppLike.PKUser;
        changesEntity.HasFile = hasFile;
        changesEntity.BatchParameter = "";

        runInTx(new Runnable() {
            @Override
            public void run() {
                String pkObjectHex = Utility.ConvertUUIDToHexString(pkObject);
                List<ChangesEntity> changesList = queryRaw(ChangesEntity.class, String.format("where PKChange=%s", pkObjectHex));
                if (changesList.size() > 0) {
                    execSQL(String.format(Locale.CHINA, "UPDATE Changes SET Synctime=%d,Status=%d,SyncType=%d," +
                                    "TableName='%s',ScopeType=2,PKCompany=%s,HasFile=%d,PKObject=%s,BatchParameter='%s' WHERE PKChange=%s",
                            changesEntity.SyncTime, changesEntity.Status, changesEntity.SyncType,
                            changesEntity.TableName, AppLike.PKCompanyHex, changesEntity.HasFile,
                            pkObjectHex, changesEntity.BatchParameter, pkObjectHex));
                } else {
                    insert(changesEntity);
                }
                SyncForwardTask.executeUnlessRunning(context, null);
            }
        });
    }
     */
    /**
     * 清空数据表
     * @param tableName
     */
    public void clearTable(String tableName) {
        String sql = "delete from " + tableName;
        execSQL(sql);
    }

}
