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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Smile on 2016/11/20.
 */

public class SMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        {
            System.out.println("短信来了");
            abortBroadcast();

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


               /* //吧信息存储进sms数据库
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
*/
            }
        }
    }
}
