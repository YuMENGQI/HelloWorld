package com.android.byc.hello.presenter;

import android.util.Log;

import com.android.byc.hello.db.CurrencyTaskRecordsEntity;
import com.android.byc.hello.db.CurrencyTasksEntity;
import com.android.byc.hello.db.UserModelEntity;
import com.android.byc.hello.network.CoinsTaskApi;
import com.android.byc.hello.network.Data;
import com.android.byc.hello.network.IRankingListContract;
import com.android.byc.hello.network.Network;
import com.android.byc.hello.network.RankingListResponse;
import com.android.byc.hello.network.Response;
import com.android.byc.hello.util.ListUtils;
import com.android.byc.hello.util.Utility;
import com.android.byc.hello.view.CurrencyTaskInfo;
import com.android.byc.hello.view.RankingListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 14:00
 * @description
 */
public class RankingListPresenter extends BasePresenter<IRankingListContract.View>
        implements IRankingListContract.Presenter {
    public RankingListPresenter(RankingListFragment view) {
        this.view = view;
        this.context = view.getContext();
        this.dbManager = new DBManager(context);
    }

    @Override
    public void fetchDataFromLocal() {

    }

    @Override
    public void fetchDataFromRemote() {
        String pkCompany = "d9201913-78f1-4c4e-9967-5fb7cf4d0473";

        Network network = Network.getInstance();
        CoinsTaskApi coinsTaskApi = network.getCoinsTaskApi();
        coinsTaskApi
                .getCurrencyList(pkCompany)
                .map(new Function<Response<List<Data>>, List<UserModelEntity>>() {
                    @Override
                    public List<UserModelEntity> apply(Response<List<Data>> dataTableResponse) throws Exception {
                        List<RankingListResponse> ranks = dataTableResponse.getData().get(0)
                                .GetEntities(RankingListResponse.class);
                        // 根据网络获取的用户，查找本地对应的用户
                        List<UserModelEntity> list = new ArrayList<>();

                        // 将从网络获取的积分插入到本地对应的用户
                        for (RankingListResponse rank : ranks) {
                            UserModelEntity userModelEntity = new UserModelEntity();
                            userModelEntity.setPKUser(UUID.randomUUID());
                            userModelEntity.setCurrencyAmount(rank.CurrencyAmount);
                            list.add(userModelEntity);
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<UserModelEntity>>() {
                    @Override
                    public void accept(List<UserModelEntity> list) {
                        if (list.size() > 0) {
                            view.refreshRankingList(list);
                            //RankingListPresenter.this.filterThenRefreshMyRank(list);
                        }
                    }
                })
                .observeOn(Schedulers.io())
                //.doOnNext(list -> dbManager.insertOrReplace(list))
                .subscribe(new Observer<List<UserModelEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserModelEntity> userModelEntities) {

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
    /**
     * 排序后获得我的余额和排名,并刷新界面
     * 如果列表中不含有本人的用户，那么从本地查询出个人用户
     * @param users 一个公司的所有人
     */
    private void filterThenRefreshMyRank(List<UserModelEntity> users) {
        String pkUser = "6342ba83-d37d-47a5-b629-8c500d52a374";
        users = ListUtils.filterThenSort(users);
        int rank = 0;
        UserModelEntity user = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPKUser().toString().equals(pkUser.toString())) {
                // 排序后获得我的余额和排名
                user = users.get(i);
                rank = i + 1;
                break;
            }
        }

        if (user == null) {
            user = dbManager.queryRaw(UserModelEntity.class, pkUser).get(0);
        }

        view.refreshMyRank(user, rank);
    }

    /**
     * 拼接查询条件
     * @param ranks ranks
     * @return conditions
     */
    private String splicingConditions(List<RankingListResponse> ranks) {
        StringBuilder conditions = new StringBuilder("where PKUser in (");
        for (RankingListResponse rank : ranks) {
            conditions.append(Utility.ConvertUUIDToHexString(rank.PKUser));
            conditions.append(",");
        }
        if (conditions.indexOf(",") > 0) {
            conditions.deleteCharAt(conditions.lastIndexOf(","));
        }
        conditions.append(")");
        return conditions.toString();
    }
}
