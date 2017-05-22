package com.chinacreator.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.chinacreator.bean.PostBean;
import com.chinacreator.utils.RxBus;

/**
 * Created by Smile on 2017/5/15.
 */

public class myIntentService extends IntentService {

    public myIntentService() {
        //必须实现父类的构造方法
        super("IntentServiceDemo");
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        return super.onBind(intent);
    }


    @Override
    public void onCreate() {
        System.out.println("onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        System.out.println("onStart");
        super.onStart(intent, startId);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        System.out.println("setIntentRedelivery");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (intent != null) {
            String action = intent.getAction();
            if (action == "UPDATE") {
                RxBus.get().post("update", new PostBean("111",111,"1111111"));
                //System.out.println("发送了广播");
            }
        }
    }
}
