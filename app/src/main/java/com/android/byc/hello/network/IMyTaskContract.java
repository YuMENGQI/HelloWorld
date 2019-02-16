package com.android.byc.hello.network;

import com.android.byc.hello.db.CurrencyTasksEntity;

import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 11:10
 * @description
 */
public interface IMyTaskContract {
    interface View {
        void refreshTaskPanel(List<CurrencyTasksEntity> currencyTasks);
    }

    interface Presenter {
        void fetchDataFromLocal();
        void fetchDataFromRemote();
    }
}
