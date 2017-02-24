package com.example.test_stutabar;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.os.Build.VERSION_CODES.M;
import static com.example.test_stutabar.PermissionUtils.CODE_MULTI_PERMISSION;
import static com.example.test_stutabar.PermissionUtils.getNoGrantedPermission;

/**
 * Created by Smile on 2016/11/23.
 */
@RuntimePermissions
public class Second_Activity extends AppCompatActivity {


    @BindView(R.id.btn_call_phone)
    Button mBtnCallPhone;
    @BindView(R.id.btn_send_sms)
    Button mBtnSendSms;
    private SystemBarTintManager mTintManager;

    PermissionChecker mPermissionChecker;
    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_second);
        ButterKnife.bind(this);
        initWindow();

        mBtnCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call_Phone();
            }
        });

        mBtnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sendsms();
            }
        });

        //Initdate();
    }

    @NeedsPermission(Manifest.permission.SEND_SMS)
    public void SendSms() {
        Sendsms();
    }


    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 2;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void Sendsms() {
        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            System.out.println("SendSms需要重新获取权限");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                return;
            }

            return;
        }*/
        // 获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        // 拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage("hello world");
        for (String text : divideContents) {
            smsManager.sendTextMessage("5556", null, text, null, null);
        }
    }

    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showRationaleForRecord(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("不给", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("挑战需要录音权限，应用将要申请录音权限")
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void showRecordDenied() {
        Toast.makeText(this, "请求失败", 0).show();
    }


    @NeedsPermission(Manifest.permission.CALL_PHONE)
    public void Call_Phone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            System.out.println("CallPhone需要重新获取权限");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
            return;
        }*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onRecordNeverAskAgain() {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 2016/11/10 打开系统设置权限
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("您已经禁止了录音权限,是否现在去开启")
                .show();
    }


    @Override
    protected void onResume() {
        super.onResume();


        /*if (frist) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
                return;
            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                return;
            }
        }
        frist = false;*/

    }

    public static final String TAG = "MainActivity";


    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));
            mTintManager.setStatusBarTintEnabled(true);
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {


       *//* switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
                System.out.println("SendSms权限的个数：" + permissions.length);
                for (String str : permissions
                        ) {
                    System.out.println("Permissions:" + str);
                }
                System.out.println("SendSms授权的结果数量:" + grantResults.length);
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("授权成功");
                    } else {
                        System.out.println("授权失败");

                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest
                                .permission
                                .SEND_SMS)) {

                            System.out.println("shouldRequest重新获取权限");
                            showMessageOKCancel("You need to allow access to Contacts",
                                    new DialogInterface.OnClickListener() {
                                        @RequiresApi(api = M)
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            *//**//*requestPermissions(new String[]{Manifest
                                            * .permission
                                                            .SEND_SMS},
                                                    REQUEST_CODE_ASK_PERMISSIONS);*//**//*
                                            ActivityCompat.requestPermissions(Second_Activity.this,
                                                    new String[]{Manifest.permission.SEND_SMS},
                                                    MY_PERMISSIONS_REQUEST_SEND_SMS);
                                        }
                                    });
                        } else {
                            System.out.println("不需要");
                        }

                    }
                }

                break;

            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                System.out.println("CallPhone权限的个数：" + permissions.length);
                for (String str : permissions
                        ) {
                    System.out.println("Permissions:" + str);
                }
                System.out.println("CallPhone授权的结果数量:" + grantResults.length);
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("授权成功");
                    } else {
                        System.out.println("授权失败");

                    }
                }
                break;
            case REQUEST_CODE_ASK_PERMISSIONS:

                //Sendsms();
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.SEND_SMS},
                            MY_PERMISSIONS_REQUEST_SEND_SMS);
                    return;
                }
                break;
        }*//*
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/
}
