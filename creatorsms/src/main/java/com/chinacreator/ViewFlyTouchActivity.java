package com.chinacreator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smile on 2017/5/17.
 */

public class ViewFlyTouchActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        List<String> mData = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            mData.add(i + "");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // 设置adapter
        MyAdapter myAdapter = new MyAdapter(this, mData);
        mRecyclerView.setAdapter(myAdapter);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
        Context mContext;
        List<String> mDatas;

        public MyAdapter(Context context, List<String> mData) {
            this.mContext = context;
            this.mDatas = mData;
        }

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = View.inflate(mContext, R.layout.recyclerviewitem, null);
            return new myViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.tv_item.setText(mDatas.get(position));

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class myViewHolder extends RecyclerView.ViewHolder {

            TextView tv_item;

            public myViewHolder(View itemView) {
                super(itemView);
                tv_item = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }
}
