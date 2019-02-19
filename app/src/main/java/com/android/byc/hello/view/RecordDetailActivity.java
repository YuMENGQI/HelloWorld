package com.android.byc.hello.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.byc.hello.MainActivity;
import com.android.byc.hello.R;
import com.android.byc.hello.adapter.RankingListAdapter;
import com.android.byc.hello.adapter.RecordDetailViewPagerAdapter;
import com.android.byc.hello.db.ChatMessageIntegral;
import com.android.byc.hello.db.CurrencyRecordsEntity;
import com.android.byc.hello.network.IRecordDetailContract;
import com.android.byc.hello.presenter.RankingListPresenter;
import com.android.byc.hello.presenter.RecordDetailPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 17:52
 * @description
 */
public class RecordDetailActivity extends AppCompatActivity implements IRecordDetailContract.View,View.OnClickListener {

    /** viewpager */
    //@BindView(R.id.vp_record_detail)
    ViewPager viewPager;
    /** 收入 */
    //@BindView(R.id.tv_income)
    TextView tvIncome;
    /** 支出 */
    //@BindView(R.id.tv_expense)
    TextView tvExpense;
    /** 收入条 */
   // @BindView(R.id.income_bar)
    View incomeBar;
    /** 支出条 */
   // @BindView(R.id.expense_bar)
    View expenseBar;

    /** adapter */
    private Button TitleBarGuideBack;
   // private TextView mTvTitleName;
    private RecordDetailViewPagerAdapter viewPagerAdapter;
    /** presenter */
    private IRecordDetailContract.Presenter presenter;

    private List<CurrencyRecordsEntity> allList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);
        TitleBarGuideBack = findViewById(R.id.TitleBarGuideBack);
        viewPager = findViewById(R.id.vp_record_detail);
        tvIncome = findViewById(R.id.tv_income);
        tvExpense = findViewById(R.id.tv_expense);
        incomeBar = findViewById(R.id.income_bar);
        expenseBar = findViewById(R.id.expense_bar);
        viewPager.setOnClickListener(this);
        tvIncome.setOnClickListener(this);
        tvExpense.setOnClickListener(this);
        incomeBar.setOnClickListener(this);
        expenseBar.setOnClickListener(this);
        TitleBarGuideBack.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        /**initTitleBar();
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordDetailActivity.this.finish();
            }
        });
        mTvTitleName.setText(getText(R.string.txt_coins_record_detail));
         */
        viewPagerAdapter = new RecordDetailViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new PageChangeListener());
        presenter = new RecordDetailPresenter(this);
        presenter.fetchData();
        int type = getIntent().getIntExtra("type", ChatMessageIntegral.TYPE_INCOME);
        viewPager.setCurrentItem(type - 1);
    }
    @Override
    public void refreshList(List<CurrencyRecordsEntity> allList) {
        this.allList = allList;
        viewPagerAdapter.setDataSet(allList);
    }

    @Override
    public void refreshPartList(List<CurrencyRecordsEntity> partList) {
        this.allList.addAll(partList);
        refreshList(this.allList);
    }
    /**
     *
     * @param context context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        context.startActivity(intent);
    }
    /**
     * 从系统消息界面进入
     *
     * @param context context
     * @param type    类型
     */
    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public void onClick(View v) {
        if (R.id.tv_income == v.getId()) {
            viewPager.setCurrentItem(0, true);
            switchTabStatus(0);
        } else if (R.id.tv_expense == v.getId()) {
            viewPager.setCurrentItem(1, true);
            switchTabStatus(1);
        }
        switch (v.getId()) {
            case R.id.TitleBarGuideBack:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
    /**
     * 0:收入，1:支出
     *
     * @param index index
     */
    private void switchTabStatus(int index) {
        if (index == 0) {
            tvIncome.setTextColor(getResources().getColor(R.color.coins_main));
            incomeBar.setVisibility(View.VISIBLE);
            tvExpense.setTextColor(getResources().getColor(R.color.gray_no_effect));
            expenseBar.setVisibility(View.INVISIBLE);
        } else if (index == 1) {
            tvIncome.setTextColor(getResources().getColor(R.color.gray_no_effect));
            incomeBar.setVisibility(View.INVISIBLE);
            tvExpense.setTextColor(getResources().getColor(R.color.coins_main));
            expenseBar.setVisibility(View.VISIBLE);
        }
    }




    /**
     * viewpager 滑动监听
     */
    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            switchTabStatus(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageScrollStateChanged(int state) { }
    }
}
