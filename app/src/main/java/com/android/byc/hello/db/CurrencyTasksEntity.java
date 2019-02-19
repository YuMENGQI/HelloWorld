package com.android.byc.hello.db;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 10:24
 * @description
 */
@Entity(nameInDb = "CurrencyTasks")
public class CurrencyTasksEntity {
    @Id
    public Long id;
    @Property(nameInDb = "Title")
    public String title = "";
    @Property (nameInDb = "Description")
    public String description = "";
    @Property (nameInDb = "Amount")
    public int amount;
    @Property(nameInDb = "LimitCount")
    public int limitCount;
    @Property(nameInDb = "CreateTime")
    public String createTime = "";
    @Property(nameInDb = "SortValue")
    public int sortValue;

    @Transient
    private List<CurrencyTaskRecordsEntity> currencyTaskRecords;

    @Generated(hash = 432242342)
    public CurrencyTasksEntity(Long id, String title, String description, int amount,
                               int limitCount, String createTime, int sortValue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.limitCount = limitCount;
        this.createTime = createTime;
        this.sortValue = sortValue;
    }

    @Generated(hash = 698119328)
    public CurrencyTasksEntity() {
    }

    public List<CurrencyTaskRecordsEntity> getCurrencyTaskRecords() {
        return currencyTaskRecords;
    }
    public void setCurrencyTaskRecords(List<CurrencyTaskRecordsEntity> currencyTaskRecords) {
        this.currencyTaskRecords = currencyTaskRecords;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAmount() {
        return this.amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getLimitCount() {
        return this.limitCount;
    }
    public void setLimitCount(int limitCount) {
        this.limitCount = limitCount;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getSortValue() {
        return this.sortValue;
    }
    public void setSortValue(int sortValue) {
        this.sortValue = sortValue;
    }

}
