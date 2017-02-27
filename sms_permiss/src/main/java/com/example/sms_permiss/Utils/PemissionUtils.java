package com.example.sms_permiss.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by Smile on 2016/11/22.
 */

public class PemissionUtils {

    public PemissionUtils(Context mContext){
        Activity activity= (Activity) mContext;
        if (Build.VERSION.SDK_INT >= 23)
            if (! Settings.canDrawOverlays(mContext)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + mContext.getPackageName()));
                                       activity .startActivityForResult(intent,10);


            }
    }
}
