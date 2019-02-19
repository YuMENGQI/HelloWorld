package com.android.byc.hello.presenter;

import android.util.Log;

import com.android.byc.hello.MainActivity;
import com.android.byc.hello.db.UserModelEntity;
import com.android.byc.hello.network.CoinsTaskApi;
import com.android.byc.hello.network.CurrencyAmountResponse;
import com.android.byc.hello.network.IMyCoinsContract;
import com.android.byc.hello.network.Network;
import com.android.byc.hello.network.Response;

import java.util.ArrayList;
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
 * @date 2019/2/18 15:32
 * @description
 */
public class MyCoinsPresenter extends BasePresenter<IMyCoinsContract.View> implements IMyCoinsContract.Presenter{
    public MyCoinsPresenter(MainActivity view) {
        this.view = view;
        this.context = view;
        this.dbManager = new DBManager(view);
    }
    @Override
    public void fetchDataFromLocal() {
       refreshCoins();

    }

    @Override
    public void refreshCoins() {
            final String pkUser = "6342ba83-d37d-47a5-b629-8c500d52a374";


            Network network = Network.getInstance();
            CoinsTaskApi coinsTaskApi = network.getCoinsTaskApi();
            coinsTaskApi
                    .getFoowwCurrency(pkUser)
                    .map(new Function<Response<CurrencyAmountResponse>, UserModelEntity>() {
                        @Override
                        public UserModelEntity apply(Response<CurrencyAmountResponse> response) {
                            int currencyAmount = response.getData().getCurrencyAmount();
                            List<UserModelEntity> userList = new ArrayList<>();
                            UserModelEntity userModelEntity = new UserModelEntity();
                            userModelEntity.setCurrencyAmount(currencyAmount);
                            userList.add(userModelEntity);
                            //dbManager.insertOrReplace(userList);
                            return userList.get(0);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<UserModelEntity>() {
                        @Override
                        public void accept(UserModelEntity userModel) {
                            view.refreshMyCoins(userModel);
                        }
                    })
                    .subscribe(new Observer<UserModelEntity>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UserModelEntity userModelEntity) {

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


