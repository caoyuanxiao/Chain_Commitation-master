package com.example.administrator.chain_commitation.BroadCastReceive;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.chain_commitation.MyActivity;
import com.example.administrator.chain_commitation.Observer.SmsMonitorObserver;
import com.example.administrator.chain_commitation.Utils.HandlerUtils;
import com.example.administrator.chain_commitation.bean.Sms_Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * 短信
 * Created by Administrator on 2016/11/18.
 */
public class SmsReceiver extends BroadcastReceiver {

    public static List<Sms_Info> sms_infos;

    @Override
    public void onReceive(Context context, Intent intent) {
        //android.provider.Telephony.SMS_DELIVER 中有默认短信才可以得到的action
        //设置默认短信之后 会收到下面的两个信息都可以获取到  所以这里插入了两次

        Log.i("SmsService", intent.getAction());

        if (sms_infos == null) {
            sms_infos = new ArrayList<>();
        }

        if (Build.VERSION.SDK_INT < 19) {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                GetDateAndInsert(context, intent);
                abortBroadcast();
            }
        } else {
            if (!Telephony.Sms.getDefaultSmsPackage(context).equals(context.getPackageName())) {
                //不是默认短信软件
                if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                    //获取信息并且插入到系统的短信数据库中
                    GetDateAndInsert(context, intent);
                }

            } else {
                if (intent.getAction().equals("android.provider.Telephony.SMS_DELIVER")) {
                    //获取信息并且插入到系统的短信数据库中
                    GetDateAndInsert(context, intent);
                }
            }
        }

        context.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true,
                new SmsMonitorObserver(HandlerUtils.GetInstance(), context));
        //设置点击跳转
       /* Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(context, MyActivity.class);*/

    }

    /**
     * 获取短信内容 并且把数据插入到系统短信中去
     *
     * @param context
     * @param intent
     */
    private void GetDateAndInsert(Context context, Intent intent) {
        SmsMessage msg = null;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            Object[] pdusObj = (Object[]) bundle.get("pdus");
            System.out.println("未读写的信息数目：" + pdusObj.length);
            for (Object p : pdusObj) {
                Sms_Info sms_info = new Sms_Info();
                msg = SmsMessage.createFromPdu((byte[]) p);
                String msgTxt = msg.getMessageBody();//得到消息的内容
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                String senderNumber = msg.getOriginatingAddress();
                sms_info.setSms_time(receiveTime);
                sms_info.setSms_content(msgTxt);
                sms_info.setSend_tel(senderNumber);
                sms_infos.add(sms_info);
                Toast.makeText(context, msgTxt, Toast.LENGTH_LONG).show();
                System.out.println("SmsReceiver发送人：" + senderNumber + "  短信内容：" + msgTxt +
                        "接受时间：" + receiveTime);

            }

            //吧信息存储进sms数据库
            ContentResolver contentResolver = context.getContentResolver();
            System.out.println("吧数据存储在数据库中");
            for (Object smsExtra : pdusObj) {
                try {
                    byte[] smsBytes = (byte[]) smsExtra;
                    SmsMessage smsMessage = SmsMessage.createFromPdu(smsBytes);
                    ContentValues values = new ContentValues();
                    values.put(Telephony.Sms.ADDRESS, smsMessage.getOriginatingAddress());
                    values.put(Telephony.Sms.BODY, smsMessage.getMessageBody());
                    values.put(Telephony.Sms.DATE, smsMessage.getTimestampMillis());
                    values.put(Telephony.Sms.READ, 0);
                    values.put(Telephony.Sms.TYPE, Telephony.Sms.MESSAGE_TYPE_INBOX);

                    contentResolver.insert(Uri.parse("content://sms"), values);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
