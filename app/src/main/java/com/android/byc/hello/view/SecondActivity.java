package com.android.byc.hello.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.byc.hello.R;

/**
 * @author yu
 * @version 1.0
 * @date 2019/1/9 16:09
 * @description
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMyTask;
    private android.widget.TextView tvRankingList;
    private View view1;
    private View view2;
    NoScrollViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if (R.id.my_task_container == v.getId()) {
            viewPager.setCurrentItem(0, true);
            switchTabStatus(0);
        } else if (R.id.ranking_list_container == v.getId()) {
            viewPager.setCurrentItem(1, true);
            switchTabStatus(1);
        }
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
}
