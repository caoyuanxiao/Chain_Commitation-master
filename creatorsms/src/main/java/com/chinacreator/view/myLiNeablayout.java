package com.chinacreator.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Smile on 2017/5/17.
 */

public class myLiNeablayout extends LinearLayout {

    private static final String TAG = "事件分发";

    public myLiNeablayout(Context context) {
        super(context);
    }

    public myLiNeablayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public myLiNeablayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "MyLinearLayout--dispatchTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "MyLinearLayout--dispatchTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MyLinearLayout--dispatchTouchEvent--ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "MyLinearLayout--onInterceptTouchEvent--ACTION_DOWN");
              break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "MyLinearLayout--onInterceptTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MyLinearLayout--onInterceptTouchEvent--ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "MyLinearLayout--onTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "MyLinearLayout--onTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MyLinearLayout--onTouchEvent--ACTION_UP");
                break;
        }

        return super.onTouchEvent(event);
    }


}
