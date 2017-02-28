package com.example.administrator.chain_commitation.Utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


import com.example.administrator.chain_commitation.MainActivity;
import com.example.administrator.chain_commitation.bean.Sms_Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.columnCount;
import static android.R.attr.id;
import static android.R.attr.phoneNumber;
import static android.R.attr.start;
import static android.R.attr.theme;
import static android.R.attr.type;


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
                        String[]{"_id", "address", "date", "read", "body", "thread_id", "status"},
                "address=" +
                        address, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String id = mCursor.getString(mCursor.getColumnIndex("_id"));
                String address1 = mCursor.getString(mCursor.getColumnIndex("address"));
                long time = mCursor.getLong(mCursor.getColumnIndex("date"));
                Date date = new Date(time);//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String receiveTime = format.format(date);
                String body = mCursor.getString(mCursor.getColumnIndex("body"));
                int read = mCursor.getInt(mCursor.getColumnIndex("read"));
                int status = mCursor.getInt(mCursor.getColumnIndex("status"));
                L.i("status" + status);

                Sms_Info msInfo = new Sms_Info(read, id, receiveTime, body, address1);
                int thread_id = mCursor.getInt(mCursor.getColumnIndex("thread_id"));

                sms_infos.add(msInfo);

            }
        }
        return sms_infos;
    }


    /**
     * 根据手机号码查询最新的手机短信内容
     * @param address
     * @return
     */
    public Sms_Info GetLastSms(String address){
        Sms_Info msInfo=null;
        Cursor mCursor = mContext.getContentResolver().query(Uri.parse("content://sms"), new
                            String[]{"_id", "address", "date", "read", "body", "thread_id", "status"},
                    "address=" +
                            address, null, null);
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    String id = mCursor.getString(mCursor.getColumnIndex("_id"));
                    String address1 = mCursor.getString(mCursor.getColumnIndex("address"));
                    long time = mCursor.getLong(mCursor.getColumnIndex("date"));
                    Date date = new Date(time);//时间
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String receiveTime = format.format(date);
                    String body = mCursor.getString(mCursor.getColumnIndex("body"));
                    int read = mCursor.getInt(mCursor.getColumnIndex("read"));
                    int status = mCursor.getInt(mCursor.getColumnIndex("status"));
                    L.i("status" + status);

                    msInfo = new Sms_Info(read, id, receiveTime, body, address1);
                    int thread_id = mCursor.getInt(mCursor.getColumnIndex("thread_id"));

                    System.out.println(address+"最新的一条号码为："+msInfo.toString());

                    return msInfo;
                }

            }
        return null;


    }


    //根据传递过来的电话号码获取短信  3解析出来短信内容
    public void FromReaderGte(String address) {
        int NoRead = 0;
        int readed = 0;
        Cursor mCursor = mContext.getContentResolver().query(Uri.parse("content://sms"), new
                        String[]{"_id", "address", "date", "read", "body", "thread_id", "status"},
                "address=" +
                        address, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String id = mCursor.getString(mCursor.getColumnIndex("_id"));
                String address1 = mCursor.getString(mCursor.getColumnIndex("address"));
                long time = mCursor.getLong(mCursor.getColumnIndex("date"));
                Date date = new Date(time);//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String receiveTime = format.format(date);
                String body = mCursor.getString(mCursor.getColumnIndex("body"));
                int read = mCursor.getInt(mCursor.getColumnIndex("read"));
                int status = mCursor.getInt(mCursor.getColumnIndex("status"));
                L.i("status" + status);
                if (read == 0) {
                    NoRead++;
                } else {
                    readed++;
                }
                Sms_Info msInfo = new Sms_Info(read, id, receiveTime, body, address1);
                int thread_id = mCursor.getInt(mCursor.getColumnIndex("thread_id"));

                sms_infos.add(msInfo);

            }
        }
        System.out.println("未读的信息数目为：" + NoRead + "读过的信息为：" + readed);

    }

    /**
     * 获取最新的一条短信   这里就是查询thread数据库
     * 并且可以获取到每个电话号码里面的message数量
     */
    public void GetLaterDate() {
        Cursor cursor = mContext.getContentResolver().query(Uri.parse("content://sms/"), new
                String[]{"* from threads --"}, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            int count = cursor.getShort(cursor.getColumnIndex("message_count"));
            //这里是最新的一条短信内容
            String snippet = cursor.getString(cursor.getColumnIndex("snippet"));
            String message_count = cursor.getString(cursor.getColumnIndex("message_count"));
            String snippet_cs = cursor.getString(cursor.getColumnIndex("snippet_cs"));
            String recipient_ids = cursor.getString(cursor.getColumnIndex("recipient_ids"));
            L.i("id:" + id + "count：" + count + "snippet:" + snippet + "snippet_cs:" + snippet_cs +
                    "recipient_ids:" + recipient_ids + "message_count:" + message_count);
            getPhoneNum(id);

        }
        L.i("Thread内容数量为：" + cursor.getCount());
        String[] columnNames = cursor.getColumnNames();
        for (String str : columnNames
                ) {
            L.i(str);
        }
    }

    /*
     type => 类型 1是接收到的，2是已发出   返回的结果 1为成功 0为失败
     根据
      */
    public static int deleteOneReceivedSms(Context context, String id) {
        Uri contentUri = Uri.parse("content://sms");
        // SELECT * FROM Persons WHERE firstname='Thomas' OR lastname='Carter'

        int delete = context.getContentResolver().delete(contentUri,
                "_id=?", new String[]{id});
        return delete;
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

    /**
     * 获取最后一条短信
     * 有些手机短信没有advanced_seen属性
     *
     * @param context
     * @return
     */
    public static Sms_Info getLastSmsInfo(Context context) {
        // Log.w("aaa", "进入getSmsChangedInfo 方法");
        String[] projection = new String[]{"_id", "address", "date",
                "date_sent", "read", "status", "type", "body"};

        Cursor cursor = context.getContentResolver().query(
                Uri.parse("content://sms/"), projection,
                null, null, "_id desc limit 1");
        //
        try {
            // Log.e("aaa", "count-- " + cursor.getCount());
            while (cursor != null && !cursor.isClosed() && cursor.moveToNext()) {
                // int columnCount = cursor.getColumnCount();
                // Log.v("aaa", "columnCount--" + columnCount);
                String id = cursor.getString(cursor
                        .getColumnIndexOrThrow("_id"));
                String address = cursor.getString(cursor
                        .getColumnIndexOrThrow("address"));
                String date = cursor.getString(cursor
                        .getColumnIndexOrThrow("date"));
                String date_sent = cursor.getString(cursor
                        .getColumnIndexOrThrow("date_sent"));
                int read = cursor.getInt(cursor
                        .getColumnIndexOrThrow("read"));
                String status = cursor.getString(cursor
                        .getColumnIndexOrThrow("status"));
                String type = cursor.getString(cursor
                        .getColumnIndexOrThrow("type"));
                String body = cursor.getString(cursor
                        .getColumnIndexOrThrow("body"));


                Sms_Info smsInfo = new Sms_Info(read, id, date, body, address);
                // Log.e("aaa", smsInfo.toString());
                // Log.e("aaa", "=============");
                return smsInfo;
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    //联系人查询的字段信息
    private static final String[] CONCAT_PROJECTION = new String[]{
            ContactsContract.PhoneLookup._ID,
            ContactsContract.PhoneLookup.DISPLAY_NAME
    };

    private static final int DISPLAY_NAME_COLUMN_INDEX = 1;

    public void GetAddressAndCount(Context mContext) {

        QueryHandler queryHandler = new QueryHandler(mContext.getContentResolver());
        startQuery(queryHandler);

    }

    private void GetIconImageFromAddress(String address) {
        //根据电话号码 查询出联系人的信息(名称)
        String name = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri
                .encode(address));
        Cursor concatCursor = mContext.getContentResolver().query(uri, CONCAT_PROJECTION, null,
                null, null);
        if (concatCursor.moveToFirst()) {
            //查询到了联系人
            name = concatCursor.getString(DISPLAY_NAME_COLUMN_INDEX);
            System.out.println("存在于联系人并且数据" + name);
        }

        String[] columnNames = concatCursor.getColumnNames();
        for (int i = 0; i < columnNames.length; i++) {
            L.i(columnNames[i]);
        }
        concatCursor.close();
    }


    //查询短信数据  必须为默认短信才可以使用
    private void startQuery(QueryHandler queryHandler) {
        String[] PROJECTION = new String[]{
                //cursor 查询 需要_id
                "sms.thread_id AS _id",
                "sms.body AS snippet",
                "groups.msg_count AS msg_count",
                "sms.address AS address",
                "sms.date AS date"
        };
        /**
         * 查询会话数据
         * token Sql id 查询结果的唯一标识  _ID
         * cookie  用来传递数据  通常VIEW
         * uri  指定查询数据的地址
         * projection   相当于SQL查询中 select 中的字段
         * selection    相当于SQL查询中 where id = ?
         * selectionArgs ?
         * orderBy      排序
         */
        Uri uri = Uri.parse("content://sms/conversations/");
        queryHandler.startQuery(0, null, uri, PROJECTION, null, null, " date DESC");
    }


    //异步查询框架 AsyncQueryHandler
    private class QueryHandler extends AsyncQueryHandler {

        public QueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            super.onQueryComplete(token, cookie, cursor);
            //更新数据(adapter 完成数据更新)  这里的查询必须设置为默认短信才可以查询
            if (cursor != null) {
                System.out.println("cursor不为空" + cursor.getColumnCount());
                while (cursor.moveToNext()) {
                    String idStr = cursor.getString(cursor.getColumnIndex("_id"));
                    String body = cursor.getString(cursor.getColumnIndex("snippet"));
                    int msg_count = cursor.getInt(cursor.getColumnIndex("msg_count"));
                    String address = cursor.getString(cursor.getColumnIndex("address"));
                    long date = cursor.getLong(cursor.getColumnIndex("date"));
                    // GetIconImageFromAddress(address);
                    System.out.println("idStr:" + idStr + "address:" + address + "msg_count:" +
                            msg_count);
                }

            } else {
                System.out.println("cursor为空");
            }

        }

    }

    /**
     * 发送短信 监听短信发送的状态以及对方接收短信的状态
     *
     * @param mContext
     */
    public void SendSms(Context mContext) {
        SendStatu(mContext);
        RecervStatu(mContext);
        // 获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        // 拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage("hello world");
        for (String text : divideContents) {
            smsManager.sendTextMessage("5556", null, text, sendIntent, backIntent);
        }
    }

    PendingIntent backIntent;

    private void RecervStatu(Context mContext) {
        //处理返回的接收状态
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        // create the deilverIntent parameter
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        backIntent = PendingIntent.getBroadcast(mContext, 0,
                deliverIntent, 0);
        mContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent intent) {
                System.out.println("短信是否接收的Action：" + intent.getAction());
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));
    }

    PendingIntent sendIntent;

    private void SendStatu(Context mContext) {
        //处理返回的发送状态
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        sendIntent = PendingIntent.getBroadcast(mContext, 0, sentIntent,
                0);
        // register the Broadcast Receivers
        mContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        System.out.println("发送成功");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        System.out.println("发送失败");
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));
    }

    /*
    *  根据thread_id检索sms库， 获得对应的号码
    * */
    public String[] getPhoneNum(String thread_id) {
        String PhoneNum = "";
        int noread_mms = 0;
        String[] info = {"", ""};
        String[] projection = new String[]
                {"thread_id", "address", "person", "body", "date", "type", "read"};

        Uri uri = Uri.parse("content://sms/");
        ContentResolver cr = mContext.getContentResolver();

        Cursor cursor = cr.query
                (
                        uri,
                        projection,
                        "thread_id=?",
                        new String[]{thread_id},
                        null
                );

        while (cursor.moveToNext()) {
            int phoneNumber = cursor.getColumnIndex("address");
            int isread = cursor.getColumnIndex("read");

            if (cursor.getString(isread).equals("0")) {
                noread_mms++;
            }

            PhoneNum = cursor.getString(phoneNumber);
        }
        System.out.println("PhoneNum:" + PhoneNum + "Noread_sms：" + noread_mms);
        info[0] = PhoneNum;
        info[1] = Integer.toString(noread_mms);

        return info;
    }
}

