package com.chinacreator.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static com.chinacreator.view.MyButton.TAG;

/**
 * Created by Smile on 2017/5/17.
 */

public class mLineaLayout extends LinearLayout {
    public mLineaLayout(Context context) {
        super(context);
    }

    public mLineaLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public mLineaLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "mLineaLayout--dispatchTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "mLineaLayout--dispatchTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "mLineaLayout--dispatchTouchEvent--ACTION_UP");
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "mLineaLayout--onInterceptTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "mLineaLayout--onInterceptTouchEvent--ACTION_MOVE");
               break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "mLineaLayout--onInterceptTouchEvent--ACTION_UP");
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "mLineaLayout--onTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "mLineaLayout--onTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "mLineaLayout--onTouchEvent--ACTION_UP");
                break;
        }

        return super.onTouchEvent(event);
    }
}
