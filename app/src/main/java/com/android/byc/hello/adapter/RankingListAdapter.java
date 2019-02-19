package com.android.byc.hello.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.byc.hello.R;
import com.android.byc.hello.db.UserModelEntity;
import com.android.byc.hello.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/18 13:28
 * @description
 */
public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.ViewHolder> {

    private List<UserModelEntity> userModels = new ArrayList<>();

    private Context context;
    /**
     * 奖牌图标
     */
    private int[] drawables = new int[] {
            R.drawable.ic_gold_medal,
            R.drawable.ic_silver_medal,
            R.drawable.ic_copper_medal
    };

    public RankingListAdapter() { }

    public RankingListAdapter(List<UserModelEntity> userModels) {
        this.userModels = ListUtils.filterThenSort(userModels);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_ranking_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModelEntity entity = userModels.get(position);
        // 前三名的排名换成图标
        if (position < 3) {
            holder.tvRanking.setVisibility(View.GONE);
            // 图标
            holder.ivRanking.setVisibility(View.VISIBLE);
            holder.ivRanking.setImageResource(drawables[position]);
        } else {
            holder.tvRanking.setVisibility(View.VISIBLE);
            holder.tvRanking.setText(String.valueOf(position + 1));
            // 图标
            holder.ivRanking.setVisibility(View.GONE);
        }
        holder.tvName.setText(entity.getUserName());
        holder.tvAmount.setText(String.valueOf(entity.getCurrencyAmount()));

        holder.tvRanking.setTextColor(context.getResources().getColor(R.color.text_black));
        holder.tvName.setTextColor(context.getResources().getColor(R.color.text_black));
        holder.tvAmount.setTextColor(context.getResources().getColor(R.color.text_black));
    }

    @Override
    public int getItemCount() {
        return userModels == null ? 0 : userModels.size();
    }

    /**
     * 更新 dataset
     * @param userModels userModels
     */
    public void setUserModels(List<UserModelEntity> userModels) {
        this.userModels = ListUtils.filterThenSort(userModels);
        this.notifyDataSetChanged();
    }

    /**
     * ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 排行榜排名
         */
        TextView tvRanking;
        /**
         * 排行榜用户名字
         */
        TextView tvName;
        /**
         * 排行榜上余额
         */
        TextView tvAmount;
        /**
         * 排行榜排名图标
         */
        ImageView ivRanking;

        ViewHolder(View itemView) {
            super(itemView);

            tvRanking = itemView.findViewById(R.id.tv_my_ranking);
            tvName = itemView.findViewById(R.id.tv_my_name);
            tvAmount = itemView.findViewById(R.id.tv_my_coins);
            ivRanking = itemView.findViewById(R.id.iv_my_ranking);
        }
    }
}
