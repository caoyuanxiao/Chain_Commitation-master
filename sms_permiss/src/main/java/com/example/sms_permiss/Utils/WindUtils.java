package com.example.sms_permiss.Utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Smile on 2016/11/21.
 */

public class WindUtils {

    Context mContext;
    WindowManager wm;

    public WindUtils(Context context){
        this.mContext=context;
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }
    private static  WindUtils mWindUtils;
    public static WindUtils GetInstance(Context context){
        if (mWindUtils==null){
            mWindUtils=new WindUtils(context);
        }
        return mWindUtils;
    }

    public int GetWindowWidth(){
       return wm.getDefaultDisplay().getWidth();
    }
    public int GetWindowHeight(){
        return  wm.getDefaultDisplay().getHeight();
    }


}
