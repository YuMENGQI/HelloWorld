package com.android.byc.hello.network;

import com.android.byc.hello.db.CurrencyRecordsEntity;

import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 17:49
 * @description
 */
public interface IRecordDetailContract {
    interface View {
        void refreshList(List<CurrencyRecordsEntity> allList);
        void refreshPartList(List<CurrencyRecordsEntity> partList);
    }


    interface Presenter {
        void fetchData();
    }
}
