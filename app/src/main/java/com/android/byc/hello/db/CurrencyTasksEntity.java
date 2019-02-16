package com.android.byc.hello.db;

import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 10:24
 * @description
 */
public class CurrencyTasksEntity {

    public Long id;
    public String title = "";
    public String description = "";
    public int amount;
    public int limitCount;
    public String createTime = "";
    public int sortValue;

    private List<CurrencyTaskRecordsEntity> currencyTaskRecords;


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
