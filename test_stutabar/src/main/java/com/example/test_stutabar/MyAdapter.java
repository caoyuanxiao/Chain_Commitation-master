package com.example.test_stutabar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Smile on 2016/11/24.
 */

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<String> items;
    List<Integer> types;
    Context mContext;



    public MyAdapter(Context context, List<String> items, List<Integer> types) {
        this.items = items;
        this.types = types;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 2) {
            return new LeftViewHolder(View.inflate(mContext, R.layout.left, null));
        }
        return new RightViewHolder(View.inflate(mContext, R.layout.right, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof LeftViewHolder) {
            ((LeftViewHolder) holder).tv_left.setText(items.get(position));
        }
        if (holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).tv_right.setText(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    public static class LeftViewHolder extends ViewHolder {

        @BindView(R.id.tv_left)
        TextView tv_left;

        public LeftViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class RightViewHolder extends ViewHolder {

        @BindView(R.id.tv_right)

        TextView tv_right;

        public RightViewHolder(View itemView) {
            super(itemView);
           ButterKnife.bind(this,itemView);


        }
    }
}
