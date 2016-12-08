package com.example.sms_permiss.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;

import com.example.sms_permiss.bean.Sms_Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.id;


/**
 * Created by Smile on 2016/11/22.
 */

public class SmsDateBaseUtils {
    Context mContext;
    List<Sms_Info> sms_infos;

    public SmsDateBaseUtils(Context context) {
        this.mContext = context;
        sms_infos = new ArrayList<>();

    }

    //根据传递过来的电话号码获取短信  3解析出来短信内容
    public List<Sms_Info> FromNumberGetdate(String address) {
        Cursor mCursor = mContext.getContentResolver().query(Uri.parse("content://sms"), new
                String[]{"address", "date", "read", "body","thread_id","status"}, "address=" + address, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String address1 = mCursor.getString(mCursor.getColumnIndex("address"));
                long time = mCursor.getLong(mCursor.getColumnIndex("date"));
                Date date = new Date(time);//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String receiveTime = format.format(date);
                String body = mCursor.getString(mCursor.getColumnIndex("body"));
                int read = mCursor.getInt(mCursor.getColumnIndex("read"));
                int status=mCursor.getInt(mCursor.getColumnIndex("status"));
                L.i("status"+status);

                Sms_Info msInfo = new Sms_Info(read, receiveTime, body, address1);
                int thread_id=mCursor.getInt(mCursor.getColumnIndex("thread_id"));

                sms_infos.add(msInfo);

            }
        }
        return sms_infos;
    }

    public void GetDate() {


        Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/"), new
                String[]{"* from threads --"}, null, null, null);
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            int count=cursor.getShort(cursor.getColumnIndex("message_count"));
            //这里是最新的一条短信内容
            String snippet=cursor.getString(cursor.getColumnIndex("snippet"));
            String snippet_cs=cursor.getString(cursor.getColumnIndex("snippet_cs"));
            L.i("id"+id+"count："+count+"address"+snippet+"snippet_cs"+snippet_cs);
        }
        L.i("Thread内容数量为：" + cursor.getCount());
        String[] columnNames = cursor.getColumnNames();
        for (String str : columnNames
                ) {
          L.i(str);
        }
    }
}
