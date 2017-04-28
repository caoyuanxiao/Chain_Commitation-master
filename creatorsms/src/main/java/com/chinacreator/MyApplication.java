package com.chinacreator;

import android.app.Application;

/**
 * Created by Smile on 2017/4/7.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //MyContactCatch.getInstance().init(this);
    }
}
