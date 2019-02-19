package com.android.byc.hello.presenter;

import android.util.Log;

import com.android.byc.hello.db.CurrencyRecordsEntity;
import com.android.byc.hello.network.CoinsTaskApi;
import com.android.byc.hello.network.Data;
import com.android.byc.hello.network.IRecordDetailContract;
import com.android.byc.hello.network.Network;
import com.android.byc.hello.network.Response;
import com.android.byc.hello.view.RecordDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 17:50
 * @description
 */
public class RecordDetailPresenter extends BasePresenter<IRecordDetailContract.View>
        implements IRecordDetailContract.Presenter {

    public RecordDetailPresenter(RecordDetailActivity view) {
        this.view = view;
        this.context = view;
        this.dbManager = new DBManager(context);
    }

    @Override
    public void fetchData() {
         Callable<List<CurrencyRecordsEntity>> callable = new Callable<List<CurrencyRecordsEntity>>() {
            @Override
            public List<CurrencyRecordsEntity> call() {
                return new ArrayList<>();
            }
        };

        Observable.fromCallable(callable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<CurrencyRecordsEntity>>() {
                    @Override
                    public void accept(List<CurrencyRecordsEntity> allList) {
                        view.refreshList(allList);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<List<CurrencyRecordsEntity>, Observable<Response<List<Data>>>>() {
                    @Override
                    public Observable<Response<List<Data>>> apply(List<CurrencyRecordsEntity> allList) {
                        String pkUser = "6342ba83-d37d-47a5-b629-8c500d52a374";
                        String createTime = ""; // 取最大的createTime
                        if (allList.size() > 0) {
                            createTime = allList.get(0).getCreateTime();
                        }
                        Network network = Network.getInstance();
                        CoinsTaskApi coinsTaskApi = network.getCoinsTaskApi();
                        return coinsTaskApi
                                .getCurrencyRecords(pkUser, createTime);
                    }
                })
                .map(new Function<Response<List<Data>>, List<CurrencyRecordsEntity>>() {
                    @Override
                    public List<CurrencyRecordsEntity> apply(Response<List<Data>> dataTable) throws Exception {
                        return dataTable.getData().get(0).GetEntities(CurrencyRecordsEntity.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<CurrencyRecordsEntity>>() {
                    @Override
                    public void accept(List<CurrencyRecordsEntity> allLocalList) {
                        view.refreshList(allLocalList);
                    }
                })
                .subscribe(new Observer<List<CurrencyRecordsEntity>>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CurrencyRecordsEntity> currencyRecordsEntities) {

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
