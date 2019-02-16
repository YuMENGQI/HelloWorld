package com.android.byc.hello.db;

import java.util.UUID;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 10:24
 * @description
 */
public class CurrencyTaskRecordsEntity {

    public Long id;
    public UUID pkUser;
    public Long currencyTaskId;
    /** 任务次数，如邀请N个人开通手机梵讯 */
    public int count;

    public CurrencyTaskRecordsEntity(Long id, UUID pkUser, Long currencyTaskId, int count) {
        this.id = id;
        this.pkUser = pkUser;
        this.currencyTaskId = currencyTaskId;
        this.count = count;
    }

    public CurrencyTaskRecordsEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getPkUser() {
        return this.pkUser;
    }
    public void setPkUser(UUID pkUser) {
        this.pkUser = pkUser;
    }
    public Long getCurrencyTaskId() {
        return this.currencyTaskId;
    }
    public void setCurrencyTaskId(Long currencyTaskId) {
        this.currencyTaskId = currencyTaskId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
