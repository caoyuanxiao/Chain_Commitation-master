package com.chinacreator;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;

/**
 * Created by Smile on 2017/3/9.
 */

public enum SmsUtils {
    SMS_UTILS;

    private SmsUtils() {

    }

    /**
     * 判断手机系统当前API是否为19以上
     *
     * @return
     */
    public static boolean HasKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * 只有api19以上才可以设置为默认短信
     *
     * @param context
     * @return
     */
    public static boolean IsDedaultSmsApp(Context context) {
        if (HasKitkat()) {
            return context.getPackageName().equals(Telephony.Sms.getDefaultSmsPackage(context));
        }
        return true;
    }

    /**
     * 设置为默认短信
     * @param context
     */
    public static void SetDefaultSmsApp(Context context) {
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, context.getPackageName());
        context.startActivity(intent);
    }
}
