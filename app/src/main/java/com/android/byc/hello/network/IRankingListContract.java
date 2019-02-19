package com.android.byc.hello.network;

import com.android.byc.hello.db.UserModelEntity;

import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 11:16
 * @description
 */
public interface IRankingListContract {
    interface View {
        void refreshMyRank(UserModelEntity user, int rank);
        void refreshRankingList(List<UserModelEntity> users);
    }

    interface Presenter {
        void fetchDataFromLocal();
        void fetchDataFromRemote();
    }
}
