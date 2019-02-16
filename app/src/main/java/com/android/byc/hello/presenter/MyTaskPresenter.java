package com.android.byc.hello.presenter;

import com.android.byc.hello.network.IMyTaskContract;
import com.android.byc.hello.view.MyTasksFragment;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:09
 * @description
 */
public class MyTaskPresenter extends BasePresenter<IMyTaskContract.View> implements IMyTaskContract.Presenter {
    private DaoSession daoSession;

    public MyTaskPresenter(MyTasksFragment view) {
    }
    @Override
    public void fetchDataFromLocal() {

    }

    @Override
    public void fetchDataFromRemote() {

    }
}
