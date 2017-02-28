package com.example.administrator.chain_commitation.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.chain_commitation.BroadCastReceive.SmsReceiver;
import com.example.administrator.chain_commitation.R;

import static android.Manifest.permission_group.SMS;
import static com.example.administrator.chain_commitation.BroadCastReceive.SmsReceiver.sms_infos;


/**
 * Created by Smile on 2016/11/21.
 */

public class SmsRecycle_Adpter extends RecyclerView.Adapter<SmsRecycle_Adpter.ViewHodler> {

    Context mContext;
    String senderNumber;

    public SmsRecycle_Adpter(Context context, String senderNumber) {
        this.mContext = context;
        this.senderNumber = senderNumber;
    }

    //创建RecycleView的子布局
    @Override
    public SmsRecycle_Adpter.ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View SmsContent = View.inflate(mContext, R.layout.sms_content, null);
        return new ViewHodler(SmsContent);
    }

    //将数据绑定在设置在界面上进行显示
    @Override
    public void onBindViewHolder(SmsRecycle_Adpter.ViewHodler holder, int position) {
        if (SmsReceiver.sms_infos != null && SmsReceiver.sms_infos.size() > 0) {
            holder.sms_time.setText(SmsReceiver.sms_infos.get(position).getSms_time());
            holder.sms_content.setText(SmsReceiver.sms_infos.get(position).getSms_content());
        }
    }

    //RecycleView的数量
    @Override
    public int getItemCount() {
        if (SmsReceiver.sms_infos != null && SmsReceiver.sms_infos.size() > 0) {
            return SmsReceiver.sms_infos.size();
        }
        return 0;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        TextView sms_content;
        TextView sms_time;

        public ViewHodler(View itemView) {
            super(itemView);
            sms_content = (TextView) itemView.findViewById(R.id.sms_content);
            sms_time = (TextView) itemView.findViewById(R.id.sms_time);
        }
    }
}
