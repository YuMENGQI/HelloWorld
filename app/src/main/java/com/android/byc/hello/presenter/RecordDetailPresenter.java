package com.android.byc.hello.presenter;

import android.util.Log;

import com.android.byc.hello.db.CurrencyRecordsEntity;
import com.android.byc.hello.myapplication.MyApplication;
import com.android.byc.hello.network.CoinsTaskApi;
import com.android.byc.hello.network.Data;
import com.android.byc.hello.network.IRecordDetailContract;
import com.android.byc.hello.network.Network;
import com.android.byc.hello.network.Response;
import com.android.byc.hello.view.RecordDetailActivity;
import java.util.List;
import java.util.UUID;
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

    public static String ConvertUUIDToHexString(String str) {
        UUID uuid = ConvertStringToUUID(str);
        return ConvertUUIDToHexString(uuid);
    }

    public static UUID ConvertStringToUUID(String str) {
        if (str == null || str.trim().length() < 10) {
            return null;
        }
        try {
            return UUID.fromString(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String ConvertUUIDToHexString(UUID uuid) {
        if (uuid == null)
            return "null";
        String strLeast = String
                .format("%016x", uuid.getLeastSignificantBits());
        String strMost = String.format("%016x", uuid.getMostSignificantBits());

        char[] chMost = new char[16];
        chMost[0] = strMost.charAt(6);
        chMost[1] = strMost.charAt(7);
        chMost[2] = strMost.charAt(4);
        chMost[3] = strMost.charAt(5);
        chMost[4] = strMost.charAt(2);
        chMost[5] = strMost.charAt(3);
        chMost[6] = strMost.charAt(0);
        chMost[7] = strMost.charAt(1);
        chMost[8] = strMost.charAt(10);
        chMost[9] = strMost.charAt(11);
        chMost[10] = strMost.charAt(8);
        chMost[11] = strMost.charAt(9);
        chMost[12] = strMost.charAt(14);
        chMost[13] = strMost.charAt(15);
        chMost[14] = strMost.charAt(12);
        chMost[15] = strMost.charAt(13);

        return String.format("X'%s'", String.valueOf(chMost) + strLeast);
    }

    @Override
    public void fetchData() {
        final String pkUser = "6342ba83-d37d-47a5-b629-8c500d52a374";
        final Callable<List<CurrencyRecordsEntity>> callable = new Callable<List<CurrencyRecordsEntity>>() {
            @Override
            public List<CurrencyRecordsEntity> call() {
               return MyApplication.getDaoSession().getCurrencyRecordsEntityDao()
                       .queryRaw("where PKUser=" + ConvertUUIDToHexString(pkUser) , " order by CreateTime DESC");
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
                .doOnNext(new Consumer<List<CurrencyRecordsEntity>>() {
                    @Override
                    public void accept(List<CurrencyRecordsEntity> partList) {
                        MyApplication.getDaoSession().getCurrencyRecordsEntityDao().insertOrReplaceInTx(partList);
                    }
                })
                .map(new Function<List<CurrencyRecordsEntity>, List<CurrencyRecordsEntity>>() {
                    @Override
                    public List<CurrencyRecordsEntity> apply(List<CurrencyRecordsEntity> partList) throws Exception {
                        return callable.call();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<CurrencyRecordsEntity>>() {
                    @Override
                    public void accept(List<CurrencyRecordsEntity> allLocalList) {
                        view.refreshList(allLocalList);
                    }
                })
                .subscribe(new Observer<List<CurrencyRecordsEntity>>() {
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
