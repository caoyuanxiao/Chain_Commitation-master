package com.chinacreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.chinacreator.service.myIntentService;

/**
 * Created by Smile on 2017/5/12.
 */

public class SecondActivity extends AppCompatActivity {

    TextView tv_response;
    Button getDate;
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tv_response = (TextView) findViewById(R.id.tv_response);
        getDate = (Button) findViewById(R.id.getDate);

        Intent intent = new Intent(SecondActivity.this, myIntentService.class);
        intent.setAction("UPDATE");
        startService(intent);

       /* System.out.println(PostBean.count);

        getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RxBus.get().post("111", new PostBean("郴州", 1, "key"));
            }
        });*/


    }
}
