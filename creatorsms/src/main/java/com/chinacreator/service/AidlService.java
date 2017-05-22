package com.chinacreator.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chinacreator.IMyAidlInterface;

/**
 * Created by Smile on 2017/5/12.
 */

public class AidlService extends Service {
    private static final String TAG = "AidlService";
    int mInt;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + Thread.currentThread());
    }

    private IBinder mIBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getUserCount() throws RemoteException {
            return getSharedPreferences("mode", MODE_MULTI_PROCESS).getInt("count", -1);
        }

        @Override
        public void setUserCount(int n) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: " + Thread.currentThread());
        return mIBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: " + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);
    }
}
