package com.chinacreator.application;

import android.app.Application;

/**
 * Created by Smile on 2017/5/12.
 */

public class MyApplication extends Application {
    Lifecycler lifecycler;

    @Override
    public void onCreate() {
        super.onCreate();
        lifecycler = new Lifecycler();
        registerActivityLifecycleCallbacks(lifecycler);
    }


    public boolean isForeground() {
        return lifecycler.isForeground;
    }
}
