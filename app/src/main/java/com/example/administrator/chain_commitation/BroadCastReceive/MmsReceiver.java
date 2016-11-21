package com.example.administrator.chain_commitation.BroadCastReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18.
 */
public class MmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.provider.Telephony.WAP_PUSH_DELIVER")) {
            //这里可以直接收到彩信
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
                        System.out.println("发送人：" + senderNumber + "  短信内容：" + msgTxt + "接受时间：" + receiveTime);
                        return;
                    }
                }
            }
        }
    }
}
