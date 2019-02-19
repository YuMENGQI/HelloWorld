package com.android.byc.hello.db;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.android.byc.hello.model.ModelHelper;
import com.android.byc.hello.model.TableMapHelper;
import com.android.byc.hello.network.ISQLiteOperate;
import com.android.byc.hello.presenter.DaoSession;
import com.android.byc.hello.util.Utility;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 13:47
 * @description
 */
public class SQLiteDataProxyForGreenDao implements ISQLiteOperate {
    private static AbstractDaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static volatile SQLiteDataProxyForGreenDao proxy;

    public SQLiteDataProxyForGreenDao() {
        //mDaoSession = mDaoMAster.newSession();
    }
    public static SQLiteDataProxyForGreenDao getInstance() {
        return ProxyHolder.sInstance;
    }

    @Override
    public boolean execSQL(String sql) {
        boolean result = true;
        try {
            if (sql.toLowerCase().contains("select")) {
                mDaoMaster.getDatabase().rawQuery(sql, null);
            } else {
                mDaoMaster.getDatabase().execSQL(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQLERROR", "execSQL：" + e.getMessage() + sql);
            result = false;
        }
        return result;
    }

    @Override
    public boolean execSQLs(String[] sqls) {
        boolean result = true;
        String currentSql = "";
        try {
            mDaoMaster.getDatabase().beginTransaction();
            currentSql = sqls[0];
            Cursor curCount = mDaoMaster.getDatabase().rawQuery(sqls[0], null);
            curCount.moveToFirst();
            int count = curCount.getInt(0);
            curCount.close();
            if (count == 0) {
                if (sqls[1] != null && sqls[1].length() > 0) {
                    currentSql = sqls[1];
                    mDaoMaster.getDatabase().execSQL(sqls[1]);
                }
            } else {
                if (sqls.length > 2 && sqls[2] != null && sqls[2].length() > 0) {
                    currentSql = sqls[2];
                    mDaoMaster.getDatabase().execSQL(sqls[2]);
                }
            }
            mDaoMaster.getDatabase().setTransactionSuccessful();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SQLERROR", "execSQLs: " + currentSql + e.getMessage());
            result = false;
        } finally {
            mDaoMaster.getDatabase().endTransaction();
        }
        return result;
    }

    @Override
    public boolean execSQLList(final List<String> sqlList) {
        boolean result = true;
        final String[] currentSqlString = {""};
        try {
            runInTx(new Runnable() {
                @Override
                public void run() {
                    for (String sql : sqlList) {
                        currentSqlString[0] = sql;
                        execSQL(sql);
                    }
                }
            });
            result = true;
        } catch (Exception e) {
            result = true;
            Log.d("SQLERROR", "execSQLList: " + e.getMessage() + currentSqlString[0]);
        }
        return result;
    }

    @Override
    public boolean execSQLs(final List<String[]> sqlList) {
        boolean result = true;
        String currentSql = "";
        try {
            mDaoMaster.getDatabase().beginTransaction();
            for (String[] arr : sqlList) {
                currentSql = arr[0];
                Cursor curCount = mDaoMaster.getDatabase().rawQuery(arr[0], null);
                curCount.moveToFirst();
                int count = curCount.getInt(0);
                curCount.close();
                if (count == 0) {
                    if (arr[1] != null && arr[1].length() > 0) {
                        currentSql = arr[1];
                        mDaoMaster.getDatabase().execSQL(arr[1]);
                    }
                } else {
                    if (arr.length > 2 && arr[2] != null && arr[2].length() > 0) {
                        currentSql = arr[2];
                        mDaoMaster.getDatabase().execSQL(arr[2]);
                    }
                }
            }
            mDaoMaster.getDatabase().setTransactionSuccessful();
            result = true;
        } catch (Exception e) {
            Log.e("SQLERROR", "execSQLs: " + currentSql + e.getMessage());
            result = false;
        } finally {
            mDaoMaster.getDatabase().endTransaction();
        }
        return result;
    }
    @Override
    public Cursor query(String sql) {
        return query(sql, null);
    }

    @Override
    public Cursor query(String sql, String[] params) {
        Cursor cursor = mDaoMaster.getDatabase().rawQuery(sql,params);
        return cursor;
    }

    @Override
    public void doQuery(ISQLQuery isqlQuery, String sql) {
        Cursor cursor = null;
        try {
            cursor = query(sql);
            isqlQuery.doQuery(cursor);
        } catch (Exception e) {
            Log.e("SQLERROR", "doQueryError: " + sql + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

    }

    @Override
    public <T> T getEntityByKey(String tableName, UUID pk) {
        if (!TextUtils.isEmpty(tableName) && pk != null) {
            List<T> list = new ArrayList<>();
            try {
                list = queryRaw((Class<T>) Class.forName(TableMapHelper.getInstance().getEntityByTable(tableName)),
                        String.format("WHERE %s=%s", HelperSync.GetPrimaryKey(tableName),
                                Utility.ConvertUUIDToHexString(pk)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public <T> long insert(T entity) {
        return mDaoSession.insert(entity);
    }

    @Override
    public <T> boolean insertOrReplace(T entity) {
        return  execSQL(ModelHelper.createSQLs(entity));
    }

    @Override
    public <T> void insertOrReplace(List<T> entityList) {
        try {
            execSQLs(ModelHelper.createSQLs(entityList));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T> void delete(T entity) {
        execSQLs(ModelHelper.createDeleteSqls(entity));

    }

    @Override
    public <T> void update(T entity) {
        execSQLs(ModelHelper.createUpdateSqls(entity));

    }

    @Override
    public void runInTx(Runnable runnable) {

    }

    @Override
    public <T> QueryBuilder<T> queryBuilder(Class<T> entityClass) {
        return mDaoSession.queryBuilder(entityClass);
    }

    @Override
    public <T> List<T> queryRaw(Class<T> entityClass, String conditions) {
        List<T> list = (List<T>) mDaoSession.getDao(entityClass).queryRaw(conditions);
        return list;
    }

    @Override
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public interface ISQLQuery {
        void doQuery(Cursor cursor);
    }
    private static class ProxyHolder {
        private static final SQLiteDataProxyForGreenDao sInstance = new SQLiteDataProxyForGreenDao();
    }
}
