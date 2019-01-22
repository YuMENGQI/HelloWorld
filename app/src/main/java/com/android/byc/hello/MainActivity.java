package com.android.byc.hello;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ImageView imageView1;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private List<CoinsTask> datas;

    /**
     * @param savedInstanceState savedInstanceState
     */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initData();
            textview1=findViewById(R.id.tv_more) ;
            textview2=findViewById(R.id.tv_myTask) ;
            textview3=findViewById(R.id.tv_paihang) ;
            textview4 = findViewById(R.id.tv_shopping);
            imageView1 = findViewById(R.id.im_detail);
            imageView1.setOnClickListener(this);
            textview1.setOnClickListener(this);
            textview2.setOnClickListener(this);
            textview3.setOnClickListener(this);
            textview4.setOnClickListener(this);
            adapter= new RecyclerViewAdapter(this,datas);
            recyclerView=  findViewById(R.id.recyclerview);
            //设置布局管理器
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            //设置adapter
            recyclerView.setAdapter(adapter);
            //添加分割线
            //recyclerView.addItemDecoration(new DividerItemDecoration(this, (int) 1f,10, Color.RED));
            recyclerView.addItemDecoration(new DividerItemDecoration(this));
            //设置item增加、移除动画
            recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        Intent intent=null;
        Uri uri;
        switch (v.getId()){
            case R.id.tv_more:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.im_detail:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_myTask:
               uri = Uri.parse("tel:888888");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.tv_paihang:
               uri = Uri.parse("tel:8888888");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.tv_shopping:
               uri = Uri.parse("1111111");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
        }
      startActivity(intent);
    }
}
