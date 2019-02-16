package com.android.byc.hello;

import android.content.Intent;

import android.net.Uri;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.byc.hello.adapter.MyTaskAdapter;
import com.android.byc.hello.db.CurrencyTaskRecordsEntity;
import com.android.byc.hello.db.CurrencyTasksEntity;
import com.android.byc.hello.network.CoinsTaskApi;
import com.android.byc.hello.network.CurrencyAmountResponse;
import com.android.byc.hello.network.Data;
import com.android.byc.hello.network.Network;
import com.android.byc.hello.network.Response;
import com.android.byc.hello.presenter.DaoSession;
import com.android.byc.hello.util.FragmentUtil;
import com.android.byc.hello.view.CurrencyTaskInfo;
import com.android.byc.hello.view.MyTasksFragment;
import com.android.byc.hello.view.NoScrollViewPager;
import com.android.byc.hello.view.RankingListFragment;
import com.android.byc.hello.view.SecondActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private View view1;
    private View view2;
    private TextView tvRecordDetail;
    private TextView tvShop;
    private TextView tvMyTask;
    private TextView tvRankingList;
    private TextView tvCoinsCount;
    NoScrollViewPager viewPager;
    private List<CoinsTask> datas;
    private ScrollView coinsTaskLayout;
    private String PKUser;
    private String UpdateTime;
    private MyTaskAdapter myTaskAdapter;
    RecyclerView rvMyTask;
    private List<Fragment> fragments = new ArrayList<>();
    private MyTasksFragment myTasksFragment;
    private RankingListFragment rankingListFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tvRecordDetail=findViewById(R.id.tv_record_detail) ;
            tvShop=findViewById(R.id.tv_shop) ;
            tvMyTask=findViewById(R.id.tv_my_task) ;
            tvRankingList = findViewById(R.id.tv_ranking_list);
            view1 = findViewById(R.id.my_task_bar);
            view2 = findViewById(R.id.ranking_list_bar);
            viewPager = findViewById(R.id.view_pager);
            tvCoinsCount = findViewById(R.id.tv_coins_count);
            view2.setOnClickListener(this);
            view1.setOnClickListener(this);
            view1.setSelected(true);
            tvRecordDetail.setOnClickListener(this);
            tvShop.setOnClickListener(this);
            tvMyTask.setOnClickListener(this);
            tvRankingList.setOnClickListener(this);
            adapter= new RecyclerViewAdapter(this,datas);
            recyclerView = findViewById(R.id.rv_my_task);

            myTasksFragment = new MyTasksFragment();
            rankingListFragment = new RankingListFragment();
            fragments.add(myTasksFragment);
            fragments.add(rankingListFragment);

            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getCount() {
                    return fragments.size();
                }
            });
            setResult(1);
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //设置adapter
            //recyclerView.setAdapter(adapter);
            /**
             * //添加分割线
            //recyclerView.addItemDecoration(new DividerItemDecoration(this, (int) 1f,10, Color.RED));
            recyclerView.addItemDecoration(new DividerItemDecoration(this));
            //设置item增加、移除动画
            recyclerView.setItemAnimator(new DefaultItemAnimator());
             **/

            getCurrencyAmount();
        }

    protected  <T extends Fragment> T findFragment(Class<T> fragmentClass) {
        return FragmentUtil.getInstance().findStackFragment(fragmentClass, null, getSupportFragmentManager());
    }

    protected <T extends Fragment> T findFragment(String fragmentTag) {
        return FragmentUtil.getInstance().findStackFragment(null, fragmentTag, getSupportFragmentManager());
    }

    private  void initData(){
        datas= new ArrayList<>();
        datas.add(new CoinsTask(10, "观看梵讯大学课程", "观看完整的课程视频才得房屋币", "去观看"));
        datas.add(new CoinsTask(10, "分享梵讯大学课程", "单个视频需他人浏览累计超过50次", "去分享"));
        datas.add(new CoinsTask(5, "添加好友", "主动添加好友，每天最多4次", "去添加"));
        datas.add(new CoinsTask(50, "邀请注册", "已成功邀请4人注册手机梵讯" , "去邀请"));
        datas.add(new CoinsTask(200, "邀请开通", "已成功邀请2人开通手机梵讯", "去邀请"));
    }
        @Override
        public void onClick(View v) {
            if (R.id.tv_my_task== v.getId()) {
                viewPager.setCurrentItem(0, true);
                switchTabStatus(0);
            } else if (R.id.tv_ranking_list == v.getId()) {
                viewPager.setCurrentItem(1, true);
                switchTabStatus(1);
            }

            Intent intent=null;
            Uri uri;
            switch (v.getId()) {

                case R.id.tv_record_detail:
                    intent = new Intent(this, SecondActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_coins_count:
                    intent = new Intent(this, SecondActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_my_task:
                    uri = Uri.parse("tel:888888");
                    intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                    break;
                case R.id.tv_ranking_list:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.tv_shop:
                    intent = new Intent(this, SecondActivity.class);
                    startActivity(intent);
                    break;
            }
            //startActivity(intent);


    }
    private void switchTabStatus(int index) {
        if (index == 0) {
            tvMyTask.setTextColor(getResources().getColor(R.color.coins_main));
            view1.setVisibility(View.VISIBLE);
            tvRankingList.setTextColor(getResources().getColor(R.color.gray_no_effect));
            view2.setVisibility(View.INVISIBLE);
        } else if (index == 1) {
            tvMyTask.setTextColor(getResources().getColor(R.color.gray_no_effect));
            view1.setVisibility(View.INVISIBLE);
            tvRankingList.setTextColor(getResources().getColor(R.color.coins_main));
            view2.setVisibility(View.VISIBLE);
        }
    }

    public void getCurrencyAmount() {
        String pkUser = "6342ba83-d37d-47a5-b629-8c500d52a374";


        Network network = Network.getInstance();
        CoinsTaskApi coinsTaskApi = network.getCoinsTaskApi();
        coinsTaskApi
                .getFoowwCurrency(pkUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Response<CurrencyAmountResponse>>() {
                    @Override
                    public void accept(Response<CurrencyAmountResponse> response) {
                        int currencyAmount = response.getData().getCurrencyCoinsAmount();
                        tvCoinsCount.setText(currencyAmount + "");
                    }
                })
                .subscribe(new Observer<Response<CurrencyAmountResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Response<CurrencyAmountResponse> currencyAmountResponseResponse) {}

                    @Override
                    public void onError(Throwable e) {
                        Log.d("", "");
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void refreshMyCoins() {

    }


}
