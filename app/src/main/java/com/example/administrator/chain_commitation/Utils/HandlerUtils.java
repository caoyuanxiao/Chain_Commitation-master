package com.example.administrator.chain_commitation.Utils;

import android.os.Handler;
import android.os.Message;

import java.security.PublicKey;

/**
 * Created by Smile on 2016/11/20.
 */

public class HandlerUtils {

    private HandlerUtils() {
    }

    public static Handler handler;

    public static Handler GetInstance() {
        if (handler != null) {
        } else {
            handler = new Handler();
        }
        return handler;
    }
}
