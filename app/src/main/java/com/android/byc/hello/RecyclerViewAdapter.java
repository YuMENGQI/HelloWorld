package com.android.byc.hello;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private  Context context;
    private  List<CoinsTask> datas;
   // private AdapterView.OnItemClickListener clickListener;
    private LayoutInflater inflater;
    public  RecyclerViewAdapter(Context context, List<CoinsTask> datas){
        inflater= LayoutInflater.from(context);
        this.datas=datas;
        this.context=context;
        //this.clickListener=clickListener;
    }
    //创建每行的View用RecyclerView.viewHolder包装
   @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
       View itemView=inflater.inflate(R.layout.activity_main_item_watch, parent, false);
       return new MyViewHolder(itemView);
    }

    //给每一行View填充数据
    @Override
    public  void  onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder,final int position) {
      /**  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(v,position);
            }
        });*/
       /** if (holder instanceof MyViewHolder){
            MyViewHolder viewHolder = (MyViewHolder)holder;
            viewHolder.title.setText(datas.get(position).getTitle());
        }*/
        final CoinsTask coinsTask = datas.get(position);
        holder.scores.setText("+" + coinsTask.score);
        holder.titles.setText(coinsTask.title);
        holder.contents.setText(coinsTask.content);
        holder.buttons.setText(coinsTask.action);
     //添加点击事件
        holder.buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onBindViewHolder", coinsTask.action);
            }
        });
    }

    //数据源的数量
    @Override
    public  int getItemCount(){
        return datas.size();
    }
   class  MyViewHolder extends RecyclerView.ViewHolder{
        private TextView scores;
        private TextView titles;
        private TextView contents;
        private Button buttons;
        public  MyViewHolder(View itemView){
            super(itemView);
            scores= itemView.findViewById(R.id.tv_description1);
            titles= itemView.findViewById(R.id.tv_title1);
            contents= itemView.findViewById(R.id.tv_content1);
            buttons= itemView.findViewById(R.id.bt_button1);
        }
    }
}
