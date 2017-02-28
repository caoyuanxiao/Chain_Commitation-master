package com.example.sms_permiss.Service;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by Administrator on 2016/11/18.
 */
public class HeadlessSmsSendService extends IntentService {


    public HeadlessSmsSendService() {
        super(HeadlessSmsSendService.class.getName());

        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (!TelephonyManager.ACTION_RESPOND_VIA_MESSAGE.equals(action)) {
            return;
        }

        Bundle extras = intent.getExtras();

        if (extras == null) {
            return;
        }

        String message = extras.getString(Intent.EXTRA_TEXT);
        Uri intentUri = intent.getData();
        String recipients = getRecipients(intentUri);
        System.out.println("信息为："+recipients);

        if (TextUtils.isEmpty(recipients)) {
            return;
        }

        if (TextUtils.isEmpty(message)) {
            return;
        }

        String[] destinations = TextUtils.split(recipients, ";");

        sendAndStoreTextMessage(getContentResolver(), destinations, message);
    }

    /**
     * get quick response recipients from URI
     */
    private String getRecipients(Uri uri) {
        String base = uri.getSchemeSpecificPart();
        int pos = base.indexOf('?');
        return (pos == -1) ? base : base.substring(0, pos);
    }

    /**
     * Send text message to recipients and store the message to SMS Content Provider
     *
     * @param contentResolver ContentResolver
     * @param destinations    recipients of message
     * @param message         message
     */


    private void sendAndStoreTextMessage(ContentResolver contentResolver, String[] destinations, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        for (String destination : destinations) {
            try {
                smsManager.sendTextMessage(destination, null, message, null, null);
                ContentValues values = new ContentValues();
                values.put(Telephony.Sms.ADDRESS, destination);
                values.put(Telephony.Sms.BODY, message);
                values.put(Telephony.Sms.DATE, System.currentTimeMillis());
                values.put(Telephony.Sms.READ, 0);
                values.put(Telephony.Sms.TYPE, Telephony.Sms.MESSAGE_TYPE_SENT);

                contentResolver.insert(Uri.parse("content://sms"), values);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
