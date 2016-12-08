package com.example.sms_permiss;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Smile on 2016/11/21.
 */

public class Show_SmsInfo {

    private Context mContext;
    private WindowManager wd;
    public Show_SmsInfo(Context context){
        mContext=context;
        wd = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    private static   Show_SmsInfo show_smsInfo;

    public  static  Show_SmsInfo GetShowInstance(Context context){
        if (show_smsInfo==null){
            show_smsInfo=new Show_SmsInfo(context);
                 }
        return show_smsInfo;
    }

    public void ShowSmsDialog(View view){
        WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();
        /*mLayoutParams.width=view.getWidth();
        mLayoutParams.height=view.getHeight();
        mLayoutParams.gravity= Gravity.CENTER;
        mLayoutParams.x=0;
        mLayoutParams.y=0;
        mLayoutParams.type= WindowManager.LayoutParams.TYPE_PHONE;
        mLayoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager
                .LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mLayoutParams.format= PixelFormat.RGBA_8888;*/

        wmParams.type=2002;  //type是关键，这里的2002表示系统级窗口，你也可以试试2003。
        //wmParams.format=1;
        wmParams.alpha=0.5f;

        /**
        *这里的flags也很关键
                *代码实际是wmParams.flags |= FLAG_NOT_FOCUSABLE;
        *40的由来是wmParams的默认属性（32）+ FLAG_NOT_FOCUSABLE（8）
        */
        wmParams.flags=40;
        wmParams.width=240;
        wmParams.height=240;
        wd.addView(view,wmParams);

        MainActivity mActivity= (MainActivity) mContext;
        mActivity.finish();


    }


}
