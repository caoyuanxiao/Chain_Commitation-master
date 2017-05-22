package com.chinacreator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chinacreator.view.MyButton;

/**
 * Created by Smile on 2017/5/16.
 */

public class ViewEventActivity extends AppCompatActivity {

    public static final String TAG = "ViewEventActivity";
    MyButton mBtn_viewenvent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewevent);

        mBtn_viewenvent = (MyButton) findViewById(R.id.btn_viewenvent);


        
        mBtn_viewenvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
            }
        });

        mBtn_viewenvent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        mBtn_viewenvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i(TAG, "onTouch: " + event.getAction());
                return false;
            }
        });
    }
}
