package com.example.sms_permiss.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sms_permiss.R;
import com.example.sms_permiss.Show_detail_Activity;
import com.example.sms_permiss.Utils.L;
import com.example.sms_permiss.Utils.SmsDateBaseUtils;
import com.example.sms_permiss.bean.Sms_Info;

import java.util.List;

import static android.view.View.inflate;

/**
 * Created by Smile on 2016/11/22.
 */

public class SmsInfo_Adapter extends RecyclerView.Adapter<SmsInfo_Adapter.ViewHolder> {
    Context mContext;
    List<Sms_Info> mSms_infos;

    public SmsInfo_Adapter(Context context, String address) {
        mContext = context;
        new SmsDateBaseUtils(mContext).GetLaterDate();
        mSms_infos = new SmsDateBaseUtils(mContext).FromNumberGetdate(address);
    }

    @Override
    public SmsInfo_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.activity_mainlistitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SmsInfo_Adapter.ViewHolder holder, final int position) {

        holder.tv_sms_time.setText(mSms_infos.get(position).getSms_time());
        holder.tv_sms_body.setText(mSms_infos.get(position).getSms_content());
        holder.tv_address.setText(mSms_infos.get(position).getSend_tel());
        holder.tv_sendstatu.setText(mSms_infos.get(position).getRead() + "");

        holder.ll_mainlistitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了：" + position, Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, Show_detail_Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSms_infos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sms_time;
        TextView tv_sms_body;
        TextView tv_address;
        TextView tv_sendstatu;
        RelativeLayout ll_mainlistitem;


        public ViewHolder(View itemView) {
            super(itemView);
            ll_mainlistitem = (RelativeLayout) itemView.findViewById(R.id.ll_mainlistitem);
            tv_sms_time = (TextView) itemView.findViewById(R.id.mainlist_time);
            tv_sms_body = (TextView) itemView.findViewById(R.id.mainlist_body);
            tv_address = (TextView) itemView.findViewById(R.id.mainlist_address);

            tv_sendstatu = (TextView) itemView.findViewById(R.id.mainlist_statu);
        }
    }
}
