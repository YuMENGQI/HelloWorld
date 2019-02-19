package com.android.byc.hello.presenter;

import com.android.byc.hello.db.CurrencyTasksEntity;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:14
 * @description
 */
public class DaoSession {

    private final Database db;
    private final Map<Class<?>, AbstractDao<?, ?>> entityToDao;

    public DaoSession(Database db, Map<Class<?>, AbstractDao<?, ?>> entityToDao) {
        this.db = db;
        this.entityToDao = entityToDao;
    }

    public Object getCurrencyTasksEntityDao(List<CurrencyTasksEntity> currencyTasks) {
        return getCurrencyTaskRecordsEntityDao();
    }

    public Object getCurrencyTaskRecordsEntityDao() {
        return getCurrencyTaskRecordsEntityDao();
    }


    /** Convenient call for {@link AbstractDao#insert(Object)}. */
    public <T> long insert(T entity) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entity.getClass());
        return dao.insert(entity);
    }

    /** Convenient call for {@link AbstractDao#insertOrReplace(Object)}. */
    public <T> long insertOrReplace(T entity) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entity.getClass());
        return dao.insertOrReplace(entity);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. */
    public <T> void refresh(T entity) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entity.getClass());
        dao.refresh(entity);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. */
    public <T> void update(T entity) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entity.getClass());
        dao.update(entity);
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. */
    public <T> void delete(T entity) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entity.getClass());
        dao.delete(entity);
    }

    /** Convenient call for {@link AbstractDao#deleteAll()}. */
    public <T> void deleteAll(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entityClass);
        dao.deleteAll();
    }

    /** Convenient call for {@link AbstractDao#load(Object)}. */
    public <T, K> T load(Class<T> entityClass, K key) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, K> dao = (AbstractDao<T, K>) getDao(entityClass);
        return dao.load(key);
    }

    /** Convenient call for {@link AbstractDao#loadAll()}. */
    public <T, K> List<T> loadAll(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, K> dao = (AbstractDao<T, K>) getDao(entityClass);
        return dao.loadAll();
    }

    /** Convenient call for {@link AbstractDao#queryRaw(String, String...)}. */
    public <T, K> List<T> queryRaw(Class<T> entityClass, String where, String... selectionArgs) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, K> dao = (AbstractDao<T, K>) getDao(entityClass);
        return dao.queryRaw(where, selectionArgs);
    }
    /** Convenient call for {@link AbstractDao#queryBuilder()}. */
    public <T> QueryBuilder<T> queryBuilder(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        AbstractDao<T, ?> dao = (AbstractDao<T, ?>) getDao(entityClass);
        return dao.queryBuilder();
    }

    public AbstractDao<?, ?> getDao(Class<? extends Object> entityClass) {
        AbstractDao<?, ?> dao = entityToDao.get(entityClass);
        if (dao == null) {
            throw new DaoException("No DAO registered for " + entityClass);
        }
        return dao;
    }

}
