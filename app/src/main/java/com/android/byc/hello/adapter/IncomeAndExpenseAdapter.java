package com.android.byc.hello.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.byc.hello.R;
import com.android.byc.hello.db.CurrencyRecordsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 17:33
 * @description
 */
public class IncomeAndExpenseAdapter extends RecyclerView.Adapter<IncomeAndExpenseAdapter.ViewHolder> {
    /** 收入 */
    public static final int INCOME = 1;
    /** 支出 */
    public static final int EXPENSE = 2;
    /***/
    private Context context;
    /***/
    private List<CurrencyRecordsEntity> records = new ArrayList<>();

    public IncomeAndExpenseAdapter() { }

    public IncomeAndExpenseAdapter(List<CurrencyRecordsEntity> records) {
        this.records = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_income_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrencyRecordsEntity record = records.get(position);

        if (record.getType() == INCOME) {
            holder.tvIncomeExpenseAmount.setText(String.format("+%s", record.getAmount()));
            holder.tvIncomeExpenseAmount.setTextColor(context.getResources().getColor(R.color.text_blue));
        } else {
            holder.tvIncomeExpenseAmount.setText(String.format("-%s", record.getAmount()));
            holder.tvIncomeExpenseAmount.setTextColor(context.getResources().getColor(R.color.textcolor_red));
        }

        holder.tvDescribtion.setText(record.getDescription());
        // 时间不显示 秒数
        holder.tvIncomeExpenseTime.setText(record.createTime.substring(0, 16));

        if (position == getItemCount() - 1) {
            holder.divideLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return records == null ? 0 : records.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /***/
        TextView tvDescribtion;
        /***/
        TextView tvIncomeExpenseTime;
        /***/
        TextView tvIncomeExpenseAmount;
        /***/
        View divideLine;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDescribtion = itemView.findViewById(R.id.tv_income_expense_describtion);
            tvIncomeExpenseTime = itemView.findViewById(R.id.tv_income_expense_time);
            tvIncomeExpenseAmount = itemView.findViewById(R.id.tv_income_expense_amount);
            divideLine = itemView.findViewById(R.id.divide_line);
        }
    }
}
