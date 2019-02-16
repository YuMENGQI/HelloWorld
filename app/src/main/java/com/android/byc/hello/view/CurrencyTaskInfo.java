package com.android.byc.hello.view;

import android.support.annotation.NonNull;

import com.android.byc.hello.db.CurrencyTaskRecordsEntity;
import com.android.byc.hello.db.CurrencyTasksEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 17:05
 * @description
 */
public class CurrencyTaskInfo {
    private List<CurrencyTasksEntity> currencyTasks;
    /** 已关联 PKUser 的任务记录 */
    private List<CurrencyTaskRecordsEntity> currencyTaskRecords;

    public CurrencyTaskInfo(@NonNull List<CurrencyTasksEntity> currencyTasks,
                            List<CurrencyTaskRecordsEntity> currencyTaskRecords) {
        this.currencyTasks = currencyTasks;
        this.currencyTaskRecords = currencyTaskRecords;
    }

    /**
     * 返回关联了currencyTaskRecords的currencyTasks
     * @return currencyTasks
     */
    public List<CurrencyTasksEntity> getAssociatedCurrencyTasks() {
        for (CurrencyTasksEntity currencyTask : currencyTasks) {
            List<CurrencyTaskRecordsEntity> associated = new ArrayList<>();
            for (CurrencyTaskRecordsEntity currencyTaskRecord : currencyTaskRecords) {
                if (currencyTaskRecord.getCurrencyTaskId().equals(currencyTask.getId())) {
                    associated.add(currencyTaskRecord);
                }
            }
            currencyTask.setCurrencyTaskRecords(associated);
        }

        return currencyTasks;
    }

    public List<CurrencyTasksEntity> getCurrencyTasks() {
        return currencyTasks;
    }

    public void setCurrencyTasks(List<CurrencyTasksEntity> currencyTasks) {
        this.currencyTasks = currencyTasks;
    }

    public List<CurrencyTaskRecordsEntity> getCurrencyTaskRecords() {
        return currencyTaskRecords;
    }

    public void setCurrencyTaskRecords(List<CurrencyTaskRecordsEntity> currencyTaskRecords) {
        this.currencyTaskRecords = currencyTaskRecords;
    }
}
