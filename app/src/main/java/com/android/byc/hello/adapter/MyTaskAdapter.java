package com.android.byc.hello.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.byc.hello.R;
import com.android.byc.hello.db.CurrencyTaskRecordsEntity;
import com.android.byc.hello.db.CurrencyTasksEntity;
import com.android.byc.hello.util.Strategy;
import com.android.byc.hello.util.StrategyMapper;

import java.util.List;


/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 16:22
 * @description
 */
public class MyTaskAdapter extends RecyclerView.Adapter<MyTaskAdapter.ViewHolder> {

        /**
         * context
         */
        private Context context;
        /**
         * 列表数据
         */
        private List<CurrencyTasksEntity> currencyTasks;

    public MyTaskAdapter() { }

    public MyTaskAdapter(List<CurrencyTasksEntity> currencyTasks) {
            this.currencyTasks = currencyTasks;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.context = parent.getContext();
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_main_item_watch, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            CurrencyTasksEntity currencyTask = currencyTasks.get(position);
            holder.tvPlusScore.setText(String.format("+%s", String.valueOf(currencyTask.getAmount())));
            holder.tvTitle.setText(currencyTask.getTitle());

            // 描述，已成功邀请*人, 若没有相关任务记录，数量置为 0
            List<CurrencyTaskRecordsEntity> taskRecords = currencyTask.getCurrencyTaskRecords();
            String description;
            if (taskRecords != null && taskRecords.size() > 0) {
                description = currencyTask.getDescription()
                        .replace("*", String.valueOf(currencyTask.getCurrencyTaskRecords().get(0).count));
            } else {
                description = currencyTask.getDescription().replace("*", String.valueOf(0));
            }
            holder.tvDescribtion.setText(description);
            final Strategy strategy = StrategyMapper.getStrategy(currencyTask.getId().toString());
            holder.btButton.setText(strategy.getButtonTitle());
            holder.btButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strategy.act(context);
                }
            });

            if (position == getItemCount() - 1) {
                holder.divideLine.setVisibility(View.GONE);
                holder.btButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        strategy.act(context);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return currencyTasks == null ? 0 : currencyTasks.size();
        }

        /**
         * ViewHolder
         */
        static class ViewHolder extends RecyclerView.ViewHolder {
            /**
             *
             */
            TextView tvPlusScore;
            /**
             *
             */
            TextView tvTitle;
            /**
             *
             */
            TextView tvDescribtion;
            /**
             *
             */
            Button btButton;
            /**
             *
             */
            View divideLine;

            ViewHolder(View itemView) {
                super(itemView);
                tvPlusScore = itemView.findViewById(R.id.plus_score);
                tvTitle = itemView.findViewById(R.id.my_task_title);
                tvDescribtion = itemView.findViewById(R.id.my_task_describtion);
                btButton = itemView.findViewById(R.id.my_task_button);
                divideLine = itemView.findViewById(R.id.divide_line);
            }
        }

        /**
         * 更新 DataSet
         * @param currencyTasks currencyTasks
         */
        public void setCurrencyTasks(List<CurrencyTasksEntity> currencyTasks) {
            this.currencyTasks = currencyTasks;
            this.notifyDataSetChanged();
        }
}
