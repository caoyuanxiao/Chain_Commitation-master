package com.example.administrator.chain_commitation.Utils;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.example.administrator.chain_commitation.MyActivity;
import com.example.administrator.chain_commitation.R;

/**
 * Created by Administrator on 2016/11/18.
 */
public class Notification {
    NotificationManager notificationManager;
    Context mContext;

    public Notification(Context context) {
        mContext = context;
        notificationManager = (NotificationManager) mContext.getSystemService(Context
                .NOTIFICATION_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void ShowXaungua() {

        android.app.Notification.Builder builder = new android.app.Notification.Builder(mContext);
        //Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        // builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap
                .ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");
        //设置点击跳转
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(mContext, MyActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(mContext, 0, hangIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);
        builder.setContentIntent(hangPendingIntent);
        notificationManager.notify(2, builder.build());
    }

}
