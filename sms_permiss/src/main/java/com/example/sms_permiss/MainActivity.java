package com.example.sms_permiss;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.sms_permiss.Adapter.SmsInfo_Adapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView sms_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitRecyclerView();
        initWindow();


    }


    private SystemBarTintManager tintManager;
    @TargetApi(19)
    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorAccent));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    private void InitRecyclerView() {
        sms_recyclerView = (RecyclerView) findViewById(R.id.recycleview_SmsList);

        //创建现行LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        //设置LayoutMananger
        sms_recyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        sms_recyclerView.setItemAnimator(new DefaultItemAnimator());
        sms_recyclerView.setAdapter(new SmsInfo_Adapter(this,"5556"));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                         Toast.makeText(MainActivity.this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void OpenInput(){
        System.out.println("打开软键盘");
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

    }
}
