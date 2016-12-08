package com.example.sms_permiss.BroadcastRecevier;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.sms_permiss.MainActivity;
import com.example.sms_permiss.Service.WindowService;
import com.example.sms_permiss.bean.Sms_Info;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Smile on 2016/11/20.
 */

public class SMS extends BroadcastReceiver {

    public  static List<Sms_Info> sms_infos;
    @Override
    public void onReceive(Context context, Intent intent) {
        {
            System.out.println("短信来了");
            abortBroadcast();
            if (sms_infos==null){
                sms_infos=new ArrayList<>();

            }
            Intent sms_info_intent = new Intent();

            SmsMessage msg = null;
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Sms_Info sms_info=new Sms_Info();
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object p : pdusObj) {
                    msg = SmsMessage.createFromPdu((byte[]) p);
                    String s = msg.toString();
                    String msgTxt = msg.getMessageBody();//得到消息的内容
                    Date date = new Date(msg.getTimestampMillis());//时间
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String senderNumber = msg.getOriginatingAddress();
                    String receiveTime = format.format(date);
                    sms_info.setSms_time(receiveTime);
                    sms_info.setSms_content(msgTxt);
                    sms_info.setSend_tel(senderNumber);
                    sms_infos.add(sms_info);

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


                sms_info_intent.setClass(context, WindowService.class);
                context.startService(sms_info_intent);
            }
        }
    }
}
