package com.example.administrator.chain_commitation.BroadCastReceive;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.administrator.chain_commitation.MainActivity;
import com.example.administrator.chain_commitation.Observer.SmsMonitorObserver;
import com.example.administrator.chain_commitation.Utils.HandlerUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        //android.provider.Telephony.SMS_DELIVER 中有默认短信才可以得到的action
        //设置默认短信之后 会收到下面的两个信息都可以获取到  所以这里插入了两次


        System.out.println("action："+intent.getAction());




        if (Build.VERSION.SDK_INT<19){
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                GetDateAndInsert(context,intent);
                    abortBroadcast();
            }
        }else{
            if (!Telephony.Sms.getDefaultSmsPackage(context).equals(context.getPackageName())){
                //不是默认短信软件
                if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                   //获取信息吧
                    GetDateAndInsert(context,intent);
                }

            }else {
                if (intent.getAction().equals("android.provider.Telephony.SMS_DELIVER")){
                    //获取信息吧
                    GetDateAndInsert(context,intent);
                }
            }
        }
        context.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, new SmsMonitorObserver(HandlerUtils.GetInstance(), context));
    }

    private void GetDateAndInsert(Context context,Intent intent) {
        SmsMessage msg = null;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            for (Object p : pdusObj) {
                msg = SmsMessage.createFromPdu((byte[]) p);
                String msgTxt = msg.getMessageBody();//得到消息的内容
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                String senderNumber = msg.getOriginatingAddress();
                if (msgTxt.equals("Testing!")) {
                    Toast.makeText(context, "success!", Toast.LENGTH_LONG)
                            .show();
                    System.out.println("success!");
                    return;
                } else {
                    Toast.makeText(context, msgTxt, Toast.LENGTH_LONG).show();
                    System.out.println("SmsReceiver发送人：" + senderNumber + "  短信内容：" + msgTxt + "接受时间：" + receiveTime);

                }
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
