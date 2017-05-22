package com.chinacreator.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by Smile on 2017/5/13.
 */

public class MessagerService extends Service {

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println("执行1111111111111");
                    break;
                case 2:
                    System.out.println("执行222222222222222222");
                    break;
            }
        }
    }

    Messenger mMessenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


}
