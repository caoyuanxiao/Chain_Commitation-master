package com.chinacreator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Smile on 2017/5/16.
 */

public class HandlerActivity extends AppCompatActivity {

    /*Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("SendMessage");
        }
    };*/

    TextView mTextView;
    Button btn_message, btn_post;

    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);


        mTextView = (TextView) findViewById(R.id.tv_ui);
        btn_post = (Button) findViewById(R.id.post);
        btn_message = (Button) findViewById(R.id.message);



        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        System.out.println("消息来了");
                        //mTextView.setText("" + msg.what);
                    }
                };
                Looper.loop();

            }
        }).start();
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自动创建一个Message对象
                mHandler.sendEmptyMessage(1);

                System.out.println(mHandler.getLooper().getThread());

            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("Post");

                    }
                });

                System.out.println(mHandler.getLooper().getThread());
            }
        });


    }
}
