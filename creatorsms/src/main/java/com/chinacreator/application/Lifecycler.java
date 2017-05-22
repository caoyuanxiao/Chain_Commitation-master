package com.chinacreator.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by Smile on 2017/5/12.
 */

public class Lifecycler implements Application.ActivityLifecycleCallbacks {

    public boolean isForeground;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        isForeground = true;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        isForeground = false;
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
