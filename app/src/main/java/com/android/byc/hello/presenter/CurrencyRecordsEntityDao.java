package com.android.byc.hello.presenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.android.byc.hello.db.CurrencyRecordsEntity;
import com.android.byc.hello.db.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 15:09
 * @description
 */
public class CurrencyRecordsEntityDao extends AbstractDao<CurrencyRecordsEntity, Long> {

    public static final String TABLENAME = "CurrencyRecords";

    /**
     * Properties of entity CurrencyRecordsEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PkUser = new Property(1, byte[].class, "pkUser", false, "PKUser");
        public final static Property PkCompany = new Property(2, byte[].class, "pkCompany", false, "PKCompany");
        public final static Property CurrencyTaskId = new Property(3, Long.class, "currencyTaskId", false, "CurrencyTaskID");
        public final static Property Description = new Property(4, String.class, "description", false, "Description");
        public final static Property Type = new Property(5, int.class, "type", false, "Type");
        public final static Property Amount = new Property(6, int.class, "amount", false, "Amount");
        public final static Property CreateTime = new Property(7, String.class, "createTime", false, "CreateTime");
    }
    private final UUID2BytesConverter pkUserConverter = new UUID2BytesConverter();
    private final UUID2BytesConverter pkCompanyConverter = new UUID2BytesConverter();
    public CurrencyRecordsEntityDao(DaoConfig config) {
        super(config);
    }
    public CurrencyRecordsEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }
    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CurrencyRecords\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PKUser\" BLOB NOT NULL ," + // 1: pkUser
                "\"PKCompany\" BLOB," + // 2: pkCompany
                "\"CurrencyTaskID\" INTEGER," + // 3: currencyTaskId
                "\"Description\" TEXT," + // 4: description
                "\"Type\" INTEGER NOT NULL ," + // 5: type
                "\"Amount\" INTEGER NOT NULL ," + // 6: amount
                "\"CreateTime\" TEXT NOT NULL );"); // 7: createTime
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_CurrencyRecords_PKUser_CreateTime_DESC ON \"CurrencyRecords\"" +
                " (\"PKUser\" ASC,\"CreateTime\" DESC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CurrencyRecords\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CurrencyRecordsEntity entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindBlob(2, pkUserConverter.convertToDatabaseValue(entity.getPkUser()));

        UUID pkCompany = entity.getPkCompany();
        if (pkCompany != null) {
            stmt.bindBlob(3, pkCompanyConverter.convertToDatabaseValue(pkCompany));
        }

        Long currencyTaskId = entity.getCurrencyTaskId();
        if (currencyTaskId != null) {
            stmt.bindLong(4, currencyTaskId);
        }

        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
        stmt.bindLong(6, entity.getType());
        stmt.bindLong(7, entity.getAmount());
        stmt.bindString(8, entity.getCreateTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CurrencyRecordsEntity entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindBlob(2, pkUserConverter.convertToDatabaseValue(entity.getPkUser()));

        UUID pkCompany = entity.getPkCompany();
        if (pkCompany != null) {
            stmt.bindBlob(3, pkCompanyConverter.convertToDatabaseValue(pkCompany));
        }

        Long currencyTaskId = entity.getCurrencyTaskId();
        if (currencyTaskId != null) {
            stmt.bindLong(4, currencyTaskId);
        }

        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
        stmt.bindLong(6, entity.getType());
        stmt.bindLong(7, entity.getAmount());
        stmt.bindString(8, entity.getCreateTime());
    }
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public CurrencyRecordsEntity readEntity(Cursor cursor, int offset) {
        CurrencyRecordsEntity entity = new CurrencyRecordsEntity( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                pkUserConverter.convertToEntityProperty(cursor.getBlob(offset + 1)), // pkUser
                cursor.isNull(offset + 2) ? null : pkCompanyConverter.convertToEntityProperty(cursor.getBlob(offset + 2)), // pkCompany
                cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // currencyTaskId
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
                cursor.getInt(offset + 5), // type
                cursor.getInt(offset + 6), // amount
                cursor.getString(offset + 7) // createTime
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, CurrencyRecordsEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPkUser(pkUserConverter.convertToEntityProperty(cursor.getBlob(offset + 1)));
        entity.setPkCompany(cursor.isNull(offset + 2) ? null : pkCompanyConverter.convertToEntityProperty(cursor.getBlob(offset + 2)));
        entity.setCurrencyTaskId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setType(cursor.getInt(offset + 5));
        entity.setAmount(cursor.getInt(offset + 6));
        entity.setCreateTime(cursor.getString(offset + 7));
    }



    @Override
    protected final Long updateKeyAfterInsert(CurrencyRecordsEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(CurrencyRecordsEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CurrencyRecordsEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
}
