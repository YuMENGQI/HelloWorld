package com.android.byc.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.byc.hello.adapter.MyTaskAdapter;
import com.android.byc.hello.db.UserModelEntity;
import com.android.byc.hello.network.IMyCoinsContract;
import com.android.byc.hello.presenter.MyCoinsPresenter;
import com.android.byc.hello.util.FragmentUtil;
import com.android.byc.hello.view.MyTasksFragment;
import com.android.byc.hello.view.NoScrollViewPager;
import com.android.byc.hello.view.RankingListFragment;
import com.android.byc.hello.view.RecordDetailActivity;
import com.android.byc.hello.view.SecondActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMyCoinsContract.View {
    private RecyclerView recyclerView;
    private View view1;
    private View view2;
    private TextView tvRecordDetail;
    private TextView tvShop;
    private TextView tvMyTask;
    private TextView tvRankingList;
    private TextView tvCoinsCount;
    private TextView mTvTitleName;
    NoScrollViewPager viewPager;
    private ScrollView coinsTaskLayout;
    private String PKUser;
    private String UpdateTime;
    private MyTaskAdapter myTaskAdapter;
    RecyclerView rvMyTask;
    private List<Fragment> fragments = new ArrayList<>();
    private MyTasksFragment myTasksFragment;
    private RankingListFragment rankingListFragment;
    private boolean fromAdvertisement=false;
    private IMyCoinsContract.Presenter presenter;

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
            recyclerView = findViewById(R.id.rv_my_task);

            if (findFragment(MyTasksFragment.class) == null
                    || findFragment(RankingListFragment.class) == null) {
                myTasksFragment = new MyTasksFragment();
                rankingListFragment = new RankingListFragment();
                fragments.add(myTasksFragment);
                fragments.add(rankingListFragment);
            } else {
                fragments.add(findFragment(MyTasksFragment.class));
                fragments.add(findFragment(RankingListFragment.class));
            }
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
            //mTvTitleName.setText("我的房屋币");
            fromAdvertisement = getIntent().getBooleanExtra("fromAdvertisement",false);
            presenter = new MyCoinsPresenter(this);
            if(fromAdvertisement) {
                presenter.refreshCoins();
            }else {
                presenter.fetchDataFromLocal();
            }
        }

    protected  <T extends Fragment> T findFragment(Class<T> fragmentClass) {
        return FragmentUtil.getInstance().findStackFragment(fragmentClass, null, getSupportFragmentManager());
    }

    protected <T extends Fragment> T findFragment(String fragmentTag) {
        return FragmentUtil.getInstance().findStackFragment(null, fragmentTag, getSupportFragmentManager());
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
            tvRecordDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecordDetailActivity.startActivity(MainActivity.this);
                }
            });
            Intent intent=null;
            Uri uri;
            switch (v.getId()) {
                case R.id.tv_coins_count:
                    intent = new Intent(this, SecondActivity.class);
                    startActivity(intent);
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
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("fromAdvertisement",false);
        context.startActivity(intent);
    }

    /**
     * 有返回值的跳转
     * @param context 上下文
     * @param requestCode requestcode
     */
    public static void startActivity(Activity context, int requestCode) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("fromAdvertisement",true);
        context.startActivityForResult(intent,requestCode);
    }

    public void refreshMyCoins(UserModelEntity userModelEntity) {
        tvCoinsCount.setText(String.valueOf(userModelEntity.getCurrencyAmount()));

    }


}
