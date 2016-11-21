package com.example.administrator.chain_commitation.Observer;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.provider.Telephony;

import com.example.administrator.chain_commitation.MainActivity;
import com.example.administrator.chain_commitation.Utils.HandlerUtils;
import com.example.administrator.chain_commitation.Utils.Notification;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;

/**
 * Created by Administrator on 2016/11/17.
 */
public class SmsMonitorObserver extends ContentObserver {

    Context mContext;
    Handler handler;

    public SmsMonitorObserver(Handler handler, Context context) {
        super(handler);
        this.mContext = context;
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        System.out.println(uri.toString());

        if (uri.toString().equals("content://sms/raw")) {
            //此时数据还没进入到数据库中
            return;
        }else{
            mContext.getContentResolver().unregisterContentObserver(new SmsMonitorObserver(HandlerUtils.GetInstance(),mContext));
        }

        Cursor mCursor = mContext.getContentResolver().query(Uri.parse("content://sms"), null, null, null, "date desc");
        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                String address = mCursor.getString(mCursor.getColumnIndex("address"));
                String body = mCursor.getString(mCursor.getColumnIndex("body"));
                System.out.println("SmsMonitorObserver发件人:" + address + "内容:" + body);

                //  new Notification(mContext).ShowXaungua();
            }
        }





    }




}
