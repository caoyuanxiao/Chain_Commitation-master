package com.example.sms_permiss.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.sms_permiss.bean.Sms_Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
                        String[]{"_id", "address", "date", "read", "body", "type", "thread_id",
                        "status"},
                "address=" +
                        address, null, "date desc");
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String id = mCursor.getString(mCursor.getColumnIndex("_id"));
                L.i("5556的短信id" + id);
                String address1 = mCursor.getString(mCursor.getColumnIndex("address"));
                long time = mCursor.getLong(mCursor.getColumnIndex("date"));
                Date date = new Date(time);//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String receiveTime = format.format(date);
                String body = mCursor.getString(mCursor.getColumnIndex("body"));
                int read = mCursor.getInt(mCursor.getColumnIndex("read"));
                int status = mCursor.getInt(mCursor.getColumnIndex("status"));

                int type = mCursor.getInt(mCursor.getColumnIndex("type"));
                System.out.println("短信类型为：" + type);
                Sms_Info msInfo = new Sms_Info(read, receiveTime, body, address1);
                int thread_id = mCursor.getInt(mCursor.getColumnIndex("thread_id"));

                sms_infos.add(msInfo);

            }
        }
        return sms_infos;
    }


    /**
     * 根据传递过来的电话号码获取短信  在主界面的windows上显示的数据
     * 根据read判断是已读短信还是未读的短信
     * 0 未读  1 已读
     */

    public List<Sms_Info> FromReaderGte(String address) {
        int NoRead = 0;
        int readed = 0;
        Cursor mCursor = mContext.getContentResolver().query(Uri.parse("content://sms"), new
                String[]{"address", "date", "read", "body", "thread_id", "status"}, "address=" +
                address, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String address1 = mCursor.getString(mCursor.getColumnIndex("address"));
                long time = mCursor.getLong(mCursor.getColumnIndex("date"));
                Date date = new Date(time);//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String receiveTime = format.format(date);
                String body = mCursor.getString(mCursor.getColumnIndex("body"));
                int read = mCursor.getInt(mCursor.getColumnIndex("read"));
                int status = mCursor.getInt(mCursor.getColumnIndex("status"));
                int type = mCursor.getInt(mCursor.getColumnIndex("type"));
                System.out.println("短信类型为：" + type);
                L.i("status" + status);
                if (read == 0) {
                    NoRead++;
                } else {
                    readed++;
                }
                Sms_Info msInfo = new Sms_Info(read, receiveTime, body, address1);
                int thread_id = mCursor.getInt(mCursor.getColumnIndex("thread_id"));
                sms_infos.add(msInfo);

            }
        }
        System.out.println("未读的信息数目为：" + NoRead + "读过的信息为：" + readed);
        return sms_infos;
    }

    /**
     * 获取最新的一条短信
     */

    public void GetLaterDate() {



        Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/"), new
                String[]{"* from threads --"}, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            int count = cursor.getShort(cursor.getColumnIndex("message_count"));
            //这里是最新的一条短信内容

            String snippet = cursor.getString(cursor.getColumnIndex("snippet"));
            String snippet_cs = cursor.getString(cursor.getColumnIndex("snippet_cs"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            L.i("id" + id + "count：" + count  + "snipet："+snippet + "snippet_cs" + snippet_cs,
                    "type" + type);
        }

        Cursor cursor1=mContext.getContentResolver().query(Uri.parse("content://mms-sms/canonical-addresses"), new String[]{"_id","address"}, null, null, null);
        System.out.println("里面的信息数量为："+cursor1.getCount());
        while (cursor1.moveToNext()){
            String address1 = cursor1.getString(cursor1.getColumnIndex("address"));
            String id = cursor1.getString(cursor1.getColumnIndex("_id"));
            System.out.println("id:"+id+"address:"+address1);
        }

        L.i("Thread内容数量为：" + cursor.getCount());
        String[] names = cursor.getColumnNames();
        for (int i = 0; i < names.length; i++) {
            System.out.println(i + ":" + names[i]);
        }


        String[] columnNames = cursor.getColumnNames();
        for (String str : columnNames
                ) {
            L.i(str);
        }


    }

    /**
     * 删除一个联系人的所有短信会话,包括+86的号码
     *
     * @param phone
     */

    public int deleteMsgSession(Context context, String phone) {
        String phoneBytitle = "";
        if (!phone.startsWith("+86")) {
            phoneBytitle = "+86" + phone;
        } else {
            phoneBytitle = phone.substring(3);
        }
        Cursor cursor =
                context.getContentResolver()
                        .query(Uri.parse("content://sms"), new String[]{"distinct thread_id"},
                                "address = ? or address = ?", new String[]{phone, phoneBytitle},
                                null);
        List<String> list = new ArrayList<String>();
        if (null != cursor) {
            if (cursor.moveToFirst()) {
                do {
                    int thread_id = cursor.getInt(0);
                    list.add(String.valueOf(thread_id));
                } while (cursor.moveToNext());
            }
        }
        if (null != cursor) {
            cursor.close();
            cursor = null;
        }
        int size = list.size();
        if (size == 0) {
            return -1;
        } else {
            int num = 0;
            for (int i = 0; i < size; i++) {
                int res = context.getContentResolver().delete(Uri.parse
                                ("content://sms/conversations/" + list.get(i)),
                        null, null);
                num = num + res;

            }
            return num;
        }
    }
}
