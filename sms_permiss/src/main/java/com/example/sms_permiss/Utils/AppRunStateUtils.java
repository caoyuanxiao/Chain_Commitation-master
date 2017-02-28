package com.example.sms_permiss.Utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ProviderInfo;

/**
 * Created by Smile on 2017/1/3.
 */

public class AppRunStateUtils {

    private static AppRunStateUtils mAppRunStateUtils;

    private AppRunStateUtils() {

    }

    public static AppRunStateUtils GetInsta() {
        if (mAppRunStateUtils == null) {
            mAppRunStateUtils = new AppRunStateUtils();
        }
        return mAppRunStateUtils;
    }

    public boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (currentPackageName != null && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }
}
