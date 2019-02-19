package com.android.byc.hello.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.byc.hello.R;
import com.android.byc.hello.adapter.RankingListAdapter;
import com.android.byc.hello.db.UserModelEntity;
import com.android.byc.hello.network.IRankingListContract;

import com.android.byc.hello.presenter.RankingListPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/16 13:31
 * @description
 */
public class RankingListFragment extends Fragment implements IRankingListContract.View {
    /**
     * 排名
     */
    @BindView(R.id.tv_my_ranking)
    TextView myRank;

    /**
     * 我的名字
     */
    @BindView(R.id.tv_my_name)
    TextView myName;
    /**
     * 我的房屋币
     */
    @BindView(R.id.tv_my_coins)
    TextView myCoins;
    /**
     * 排行榜列表
     */
    @BindView(R.id.rv_ranking_list)
    RecyclerView rvRankingList;

    private IRankingListContract.Presenter presenter;
    private RankingListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_list,container,false);
        rvRankingList =  view.findViewById(R.id.rv_ranking_list);
        myRank = view.findViewById(R.id.tv_my_ranking);
        myName = view.findViewById(R.id.tv_my_name);
        myCoins = view.findViewById(R.id.tv_my_coins);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,false);
         rvRankingList.setLayoutManager(layoutManager);
        adapter = new RankingListAdapter();
        rvRankingList.setAdapter(adapter);
        myRank.setText("未上榜");
        myName.setText(" ee");
        myCoins.setText("-");
        presenter = new RankingListPresenter(this);
        presenter.fetchDataFromLocal();
        presenter.fetchDataFromRemote();
    }

    @Override
    public void refreshMyRank(UserModelEntity user, int rank) {
        myName.setText(user.getUserName());
        if (rank <= 0) {
            myRank.setText("未上榜");
            myCoins.setText("-");
        } else {
            myRank.setText(String.valueOf(rank));
            String coins = user.getCurrencyAmount() == 0 ? "-" : String.valueOf(user.getCurrencyAmount());
            myCoins.setText(coins);
        }

    }

    @Override
    public void refreshRankingList(List<UserModelEntity> users) {
        adapter.setUserModels(users);

    }
}
