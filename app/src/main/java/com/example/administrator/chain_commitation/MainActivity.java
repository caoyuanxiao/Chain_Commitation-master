package com.example.administrator.chain_commitation;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.chain_commitation.Observer.SmsMonitorObserver;
import com.example.administrator.chain_commitation.Utils.HandlerUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_ASK_CALL_PHONE = 1;
    NotificationManager notificationManager;

    Button button;
    SharedPreferences.Editor edit;

    private SharedPreferences sp;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    break;
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化sp
        InitSp();

        notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        findViewById(R.id.nomal_notification).setOnClickListener(this);
        findViewById(R.id.Folding_notification).setOnClickListener(this);
        findViewById(R.id.xuangua_notification).setOnClickListener(this);
        button = (Button) findViewById(R.id.change_default_app);
        button.setOnClickListener(this);
        int i = checkMode();
        System.out.println("mode为：" + i);
    }

    String defaultSmsPackage;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void InitSp() {
        sp=getSharedPreferences("sms_monitor",MODE_PRIVATE);
        edit = sp.edit();
        if (sp.getBoolean("first",true)){
            edit.putBoolean("first",false);
            if (Build.VERSION.SDK_INT>=19) {
                 defaultSmsPackage= Telephony.Sms.getDefaultSmsPackage(this);
            }
            edit.putString("defaultSmsPackage",defaultSmsPackage);
            edit.commit();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_default_app:
                IsDefaultAPP();
                break;
            case R.id.Folding_notification:
                ShowFolding();
                break;
            case R.id.xuangua_notification:
                ShowXaungua();
                break;
            case R.id.nomal_notification:
                ShownomalPopuwind();
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ShowXaungua() {
        Notification.Builder builder = new Notification.Builder(this);
        //Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        // builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");

        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);
        builder.setContentIntent(hangPendingIntent);
        notificationManager.notify(2, builder.build());
    }

    private void ShownomalPopuwind() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        builder.setContentText("短信来了  你看看");
        notificationManager.notify(0, builder.build());
    }
    //来下来显示具体的信息
    private void ShowFolding() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠式通知");
        //用RemoteViews来创建自定义Notification视图
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.folding_layout);
        Notification notification = builder.build();
        //指定展开时的视图
        notification.bigContentView = remoteViews;
        notificationManager.notify(1, notification);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                //上面已经写好的拨号方法
            }
        } else {
            //上面已经写好的拨号方法

        }

        /*final String myPackageName = getPackageName();
        if (!Telephony.Sms.getDefaultSmsPackage(this).equals(myPackageName)) {
            // App is not default.
            // Show the "not currently set as the default SMS app" interface
            View viewGroup = findViewById(R.id.not_default_app);
            viewGroup.setVisibility(View.VISIBLE);

            // Set up a button that allows the user to change the default SMS app
            Button button = (Button) findViewById(R.id.change_default_app);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent =
                            new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                            myPackageName);
                    startActivity(intent);
                }
            });
        } else {
            // App is the default.
            // Hide the "not currently set as the default SMS app" interface
            View viewGroup = findViewById(R.id.not_default_app);
            viewGroup.setVisibility(View.GONE);
        }*/


    }


    /**
     * 检查此APP是否为默认的短信APP
     * true 取消默认
     * false 设置为默认的短信APP
     */
    private void IsDefaultAPP() {

        String defaultSmsPackage = sp.getString("defaultSmsPackage", "");
        final String myPackageName = getPackageName();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                   // getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, new SmsMonitorObserver(new Handler(), this));
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private int checkMode() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        Class c = appOps.getClass();
        try {
            Class[] cArg = new Class[3];
            cArg[0] = int.class;
            cArg[1] = int.class;
            cArg[2] = String.class;
            Method lMethod = c.getDeclaredMethod("checkOp", cArg);
            return (Integer) lMethod.invoke(appOps, 15, Binder.getCallingUid(), getPackageName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //getContentResolver().unregisterContentObserver(new SmsMonitorObserver(HandlerUtils.GetInstance(),this));
    }
}
