package com.example.sms_permiss.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sms_permiss.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smile on 2016/11/23.
 */

public class ShowDetail_Adapter extends RecyclerView.Adapter<ShowDetail_Adapter.ViewHolder> {

    List<String> lisStrings;
    Context mContext;

    public ShowDetail_Adapter(Context context) {
        this.mContext = context;
        InitDate();
    }

    private void InitDate() {
        lisStrings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lisStrings.add(i + "");
        }
    }

    @Override
    public ShowDetail_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.activity_showdetai_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowDetail_Adapter.ViewHolder holder, int position) {
         holder.tv_showdetail_item.setText(lisStrings.get(position));
    }



    @Override
    public int getItemCount() {
        return lisStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_showdetail_item;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_showdetail_item = (TextView) itemView.findViewById(R.id.tv_showdetail_item);
        }
    }
}
