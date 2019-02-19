package com.android.byc.hello.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.byc.hello.R;
import com.android.byc.hello.db.CurrencyRecordsEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 17:41
 * @description
 */
public class RecordDetailViewPagerAdapter extends PagerAdapter {
    /***/
    private List<CurrencyRecordsEntity> income = new ArrayList<>();
    /***/
    private List<CurrencyRecordsEntity> expense = new ArrayList<>();
    /***/
    private IncomeAndExpenseAdapter incomeAdapter;
    /***/
    private IncomeAndExpenseAdapter expenseAdapter;
    /** 收入空白页 */
    private View incomeEmptyView;
    /** 支出空白页 */
    private View expenseEmptyView;

    public RecordDetailViewPagerAdapter() {
        incomeAdapter = new IncomeAndExpenseAdapter(income);
        expenseAdapter = new IncomeAndExpenseAdapter(expense);
    }

    public RecordDetailViewPagerAdapter(List<CurrencyRecordsEntity> allList) {
        this();
        setDataSet(allList);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // return super.instantiateItem(container, position);
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_income_expense_recycler, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        if (position == 0) {
            incomeEmptyView = view.findViewById(R.id.empty_view);
            recyclerView.setAdapter(incomeAdapter);
        } else if (position == 1) {
            expenseEmptyView = view.findViewById(R.id.empty_view);
            recyclerView.setAdapter(expenseAdapter);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    /**
     * @param allList allList
     * divideList(allList) 之后 incomeAdapter 和 expenseAdapter 的 list 内容已经变化
     * 直接 notifyDataSetChanged
     */
    public void setDataSet(List<CurrencyRecordsEntity> allList) {
        divideList(allList);
        incomeAdapter.notifyDataSetChanged();
        expenseAdapter.notifyDataSetChanged();
    }

    /**
     * @param allList allList
     */
    private void divideList(List<CurrencyRecordsEntity> allList) {
        income.clear();
        expense.clear();

        for (CurrencyRecordsEntity currencyRecords : allList) {
            if (currencyRecords.getType() == 1) {
                income.add(currencyRecords);
            } else {
                expense.add(currencyRecords);
            }
        }
        if (incomeEmptyView != null) {
            incomeEmptyView.setVisibility(income.size() == 0 ? View.VISIBLE : View.GONE);
        }
        if (expenseEmptyView != null) {
            expenseEmptyView.setVisibility(expense.size() == 0 ? View.VISIBLE : View.GONE);
        }

        Collections.sort(income, new Comparator<CurrencyRecordsEntity>() {
            @Override
            public int compare(CurrencyRecordsEntity o1, CurrencyRecordsEntity o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        Collections.sort(expense, new Comparator<CurrencyRecordsEntity>() {
            @Override
            public int compare(CurrencyRecordsEntity o1, CurrencyRecordsEntity o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
    }
}
