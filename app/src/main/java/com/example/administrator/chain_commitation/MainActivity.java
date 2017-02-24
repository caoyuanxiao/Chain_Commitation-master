package com.example.administrator.chain_commitation;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
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
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.chain_commitation.Observer.SmsMonitorObserver;
import com.example.administrator.chain_commitation.Utils.HandlerUtils;
import com.example.administrator.chain_commitation.Utils.SmsDateBaseUtils;
import com.example.administrator.chain_commitation.bean.Sms_Info;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_ASK_CALL_PHONE = 1;
    NotificationManager notificationManager;

    Button button;
    SharedPreferences.Editor edit;

    private SharedPreferences sp;

    SmsDateBaseUtils mSmsDateBaseUtils;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化sp
        InitSp();

        mSmsDateBaseUtils = new SmsDateBaseUtils(this);
        notificationManager = (android.app.NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);
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
        sp = getSharedPreferences("sms_monitor", MODE_PRIVATE);
        edit = sp.edit();
        if (sp.getBoolean("first", true)) {
            edit.putBoolean("first", false);
            if (Build.VERSION.SDK_INT >= 19) {
                defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this);
            }
            edit.putString("defaultSmsPackage", defaultSmsPackage);
            edit.commit();
        }

    }

    Sms_Info lastSmsInfo;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_default_app:
                IsDefaultAPP();
                break;
            case R.id.Folding_notification:
                // ShowFolding();
                int i = SmsDateBaseUtils.deleteOneReceivedSms(this, lastSmsInfo.getId());
                System.out.println("删除的返回类型：" + i);

                break;
            case R.id.xuangua_notification:
                lastSmsInfo = SmsDateBaseUtils.getLastSmsInfo(this);
                if (lastSmsInfo != null) {
                    System.out.println("lastSmsInfo：" + lastSmsInfo.toString());
                }

                //  mSmsDateBaseUtils.GetLaterDate();

                break;
            case R.id.nomal_notification:
                //发送短信
                //  SendSms();
                mSmsDateBaseUtils.SendSms(this);
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ShowXaungua() {

        Notification.Builder builder = new Notification.Builder(this);
        /*Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);*/
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");

        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, null, PendingIntent
                .FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);
        builder.setContentIntent(hangPendingIntent);
        notificationManager.notify(2, builder.build());

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ShowFolding() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn" +
                ".net/itachi85/"));
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
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest
                    .permission.READ_SMS);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                        .READ_SMS}, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            }

           /* if (Settings.canDrawOverlays(this)) {

            } else {
                //android 6.0之后 需要创建window上面的悬浮窗体
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }*/
        }
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
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

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
            // Set up a button that allows the user to change the default SMS app

            Intent intent =
                    new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
                    myPackageName);
            startActivity(intent);

        } else {
            Toast.makeText(this, "已经设置为默认短信了", 0).show();
            //现在已经是默认的APP了  点击则还原默认的短信软件
            /*Intent intent1 = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent1.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, defaultSmsPackage);
            startActivity(intent1);
            System.out.println("本APP已经是默认的短信APP");*/
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(new SmsMonitorObserver(HandlerUtils
                .GetInstance(), this));
    }
}
