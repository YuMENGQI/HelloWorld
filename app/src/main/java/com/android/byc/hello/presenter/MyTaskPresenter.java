package com.android.byc.hello.presenter;

import android.util.Log;

import com.android.byc.hello.db.CurrencyTaskRecordsEntity;
import com.android.byc.hello.db.CurrencyTasksEntity;
import com.android.byc.hello.network.CoinsTaskApi;
import com.android.byc.hello.network.Data;
import com.android.byc.hello.network.IMyTaskContract;
import com.android.byc.hello.network.Network;
import com.android.byc.hello.network.Response;
import com.android.byc.hello.view.CurrencyTaskInfo;
import com.android.byc.hello.view.MyTasksFragment;

import java.util.List;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:09
 * @description
 */
public class MyTaskPresenter extends BasePresenter<IMyTaskContract.View> implements IMyTaskContract.Presenter {
    //private DaoSession daoSession;

    public MyTaskPresenter(MyTasksFragment view) {
        this.view = view;
        this.context = view.getContext();
        //this.dbManager = new DBManager(context);
        //daoSession = dbManager.getDaoSession();
    }
    @Override
    public void fetchDataFromLocal() {

    }

    @Override
    public void fetchDataFromRemote() {
        String pkUser = "6342ba83-d37d-47a5-b629-8c500d52a374";
        String updateTime = "";

        Network network = Network.getInstance();
        CoinsTaskApi coinsTaskApi = network.getCoinsTaskApi();
        coinsTaskApi
                .getCurrencyTaskInfo(pkUser,updateTime)
                .map(new Function<Response<List<Data>>, CurrencyTaskInfo>() {
                    @Override
                    public CurrencyTaskInfo apply(Response<List<Data>> dataTableResponse) throws Exception {
                        List<CurrencyTasksEntity> currencyTasks = dataTableResponse.getData().get(0)
                                .GetEntities(CurrencyTasksEntity.class);
                        List<CurrencyTaskRecordsEntity> currencyTaskRecords = dataTableResponse.getData().get(1)
                                .GetEntities(CurrencyTaskRecordsEntity.class);
                        return new CurrencyTaskInfo(currencyTasks, currencyTaskRecords);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<CurrencyTaskInfo>() {
                    @Override
                    public void accept(CurrencyTaskInfo currencyTasks) {
                        view.refreshTaskPanel(currencyTasks.getAssociatedCurrencyTasks());
                    }
                })
                .subscribe(new Observer<CurrencyTaskInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CurrencyTaskInfo currencyTaskInfo) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "onError: ");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
