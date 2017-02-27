package com.example.sms_permiss;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.sms_permiss.Adapter.SmsInfo_Adapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView sms_recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitSp();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("yuanxiao");
        GetPermission();
        initWindow();
        IsDefaultAPP();
    }

    String defaultSmsPackage;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void InitSp() {
        sp = getSharedPreferences("MySmsInfo", MODE_PRIVATE);
        edit = sp.edit();
        if (!sp.getBoolean("first", true)) {
            edit.putBoolean("first", true);
            if (Build.VERSION.SDK_INT >= 19) {
                defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this);
            }
            edit.putString("defaultSmsPackage", defaultSmsPackage);
            edit.commit();
        }

    }

    /**
     * 检查此APP是否为默认的短信APP
     * true 取消默认
     * false 设置为默认的短信APP
     */
    private void IsDefaultAPP() {

        String defaultSmsPackage = sp.getString("defaultSmsPackage", "");
        System.out.println("默认的短信package：" + defaultSmsPackage);
        final String myPackageName = getPackageName();
        System.out.println("My的短信package：" + myPackageName);
        if (Build.VERSION.SDK_INT < 19) {
            //使用短信拦截功能去获取短信
            /*Intent intent =
                    new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                    myPackageName);
            startActivity(intent);*/
            return;
        }
        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
            //次软件不是默认的APP 则设置为默认的APP
            System.out.println("本APP不是默认的短信APP");
            // App is not default.
            // Show the "not currently set as the default SMS app" interface
           /* View viewGroup = findViewById(R.id.not_default_app);
            viewGroup.setVisibility(View.VISIBLE);*/

            // Set up a button that allows the user to change the default SMS app

            Intent intent =
                    new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                    myPackageName);
            startActivity(intent);

        } else {
            //现在已经是默认的APP了  点击则还原默认的短信软件
            // App is the default.
            // Hide the "not currently set as the default SMS app" interface
            // View viewGroup = findViewById(R.id.not_default_app);
            // viewGroup.setVisibility(View.GONE);

            Intent intent1 = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent1.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsPackage);
            startActivity(intent1);
            System.out.println("本APP已经是默认的短信APP");
    }
    }

    private void GetPermission() {
        if (Build.VERSION.SDK_INT >= 23) {

            if (Settings.canDrawOverlays(this)) {

           } else {
                //android 6.0之后 需要创建window上面的悬浮窗体
               Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }


            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest
                    .permission.READ_SMS);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                        .READ_SMS}, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }

        }
    }


    private SystemBarTintManager tintManager;

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorAccent));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    private void InitRecyclerView() {
        System.out.println("初始化recycler");
        sms_recyclerView = (RecyclerView) findViewById(R.id.recycleview_SmsList);

        //创建现行LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        //设置LayoutMananger
        sms_recyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        sms_recyclerView.setItemAnimator(new DefaultItemAnimator());
        sms_recyclerView.setAdapter(new SmsInfo_Adapter(this, "5556"));
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

    public static final int REQUEST_CODE_ASK_CALL_PHONE = 1;


    @Override
    protected void onResume() {
        super.onResume();
        InitRecyclerView();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    // getContentResolver().registerContentObserver(Uri.parse("content://sms"),
                    // true, new SmsMonitorObserver(new Handler(), this));
                    InitRecyclerView();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;




        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void OpenInput() {
        System.out.println("打开软键盘");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

    }
}
