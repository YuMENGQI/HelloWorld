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


import com.android.byc.hello.R;
import com.android.byc.hello.adapter.MyTaskAdapter;
import com.android.byc.hello.db.CurrencyTasksEntity;
import com.android.byc.hello.network.IMyTaskContract;
import com.android.byc.hello.presenter.MyTaskPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 16:20
 * @description
 */
public class MyTasksFragment extends Fragment {
    @BindView(R.id.rv_my_task)
    RecyclerView rvMyTask;

    private MyTaskAdapter myTaskAdapter;
    private IMyTaskContract.Presenter presenter;
    private RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tasks,container,false);
        mRecyclerView =  view.findViewById(R.id.rv_my_task);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        linearLayoutManager = new LinearLayoutManager(getContext(),
        LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        myTaskAdapter = new MyTaskAdapter();
        mRecyclerView.setAdapter(myTaskAdapter);
        presenter = new MyTaskPresenter(this);
        presenter.fetchDataFromLocal();
        presenter.fetchDataFromRemote();
        List<CurrencyTasksEntity> currencyTasks = new ArrayList<>();

        currencyTasks.add(new CurrencyTasksEntity(1L,"观看梵讯大学课程", "观看完整的课程视频才得房屋币", 10,1, " ",0));
        currencyTasks.add(new CurrencyTasksEntity(2L,"分享梵讯大学课程", "单个视频需他人浏览累计超过50次", 10,1, " ",0));
        currencyTasks.add(new CurrencyTasksEntity(3L,"添加好友", "主动添加好友，每天最多4次", 5,1, " ",0));
        currencyTasks.add(new CurrencyTasksEntity(4L,"邀请注册", "已成功邀请*人注册手机梵讯", 50,1, " ",0));
        currencyTasks.add(new CurrencyTasksEntity(5L,"邀请开通", "已成功邀请*人开通手机梵讯", 200,1, " ",0));


        myTaskAdapter.setCurrencyTasks(currencyTasks);
    }
}

