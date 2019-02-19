package com.android.byc.hello.network;

import com.android.byc.hello.db.UserModelEntity;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 15:33
 * @description
 */
public interface IMyCoinsContract {
    interface View {
        void refreshMyCoins(UserModelEntity userModelEntity);
    }
    interface Presenter {
        void fetchDataFromLocal();
        void refreshCoins();
    }
}
