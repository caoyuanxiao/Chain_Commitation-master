package com.example.sms_permiss.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sms_permiss.Adapter.SmsRecycle_Adpter;
import com.example.sms_permiss.MainActivity;
import com.example.sms_permiss.R;
import com.example.sms_permiss.Utils.AppRunStateUtils;
import com.example.sms_permiss.bean.Sms_Info;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Smile on 2016/11/21.
 */

public class WindowService extends Service implements View.OnClickListener {
    private WindowManager wManager;// 窗口管理者
    private WindowManager.LayoutParams mParams;// 窗口的属性
    private View myView;
    private boolean flag = true;
    private List<Sms_Info> sms_infos;
    private String senderNumber;


    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    //多次创建服务 但是oncreat只会执行一次
    @Override
    public void onCreate() {

        {
            super.onCreate();

            myView = LayoutInflater.from(this).inflate(R.layout.sms_dialog, null);
            //  myView = View.inflate(this, R.layout.sms_dialog, null);
            wManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
            mParams = new WindowManager.LayoutParams();
            mParams.type = 2002;  //type是关键，这里的2002表示系统级窗口，你也可以试试2003。
            //可以去黑边
            mParams.format = 1;
            mParams.alpha = 1.0f;
            /**
             *这里的flags也很关键
             *代码实际是wmParams.flags |= FLAG_NOT_FOCUSABLE;
             *40的由来是wmParams的默认属性（32）+ FLAG_NOT_FOCUSABLE（8）
             */

            mParams.flags = 32;
            //设置透明度
            //wmParams.alpha=0f;

            mParams.width = wManager.getDefaultDisplay().getWidth() - 60;
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;


        }
    }

    RecyclerView sms_recyclerView;
    TextView sms_tel_up, sms_tel_down;
    EditText edt_send_sms;
    ImageView iv_send_sms, iv_sms_close;
    LinearLayout ll_sms_delet, ll_sms_todo, ll_sms_opne;

    private void InitMyView(View myview) {
        sms_recyclerView = (RecyclerView) myview.findViewById(R.id.recycleview_SmsContent);
        sms_tel_up = (TextView) myview.findViewById(R.id.sms_tel_up);
        sms_tel_down = (TextView) myview.findViewById(R.id.sms_tel_down);

        edt_send_sms = (EditText) myview.findViewById(R.id.edt_sendsms);
        iv_send_sms = (ImageView) myview.findViewById(R.id.send_sms);
        iv_sms_close = (ImageView) myview.findViewById(R.id.close);

        ll_sms_delet = (LinearLayout) myview.findViewById(R.id.ll_delete);
        ll_sms_todo = (LinearLayout) myview.findViewById(R.id.ll_todo_sms);
        ll_sms_opne = (LinearLayout) myview.findViewById(R.id.ll_open_sms);

        InitRecyclerView();
        SetOnclikListener();
    }

    private void SetOnclikListener() {
        iv_sms_close.setOnClickListener(this);
        ll_sms_opne.setOnClickListener(this);
    }

    //设置recycleView的adapter
    LinearLayoutManager mLayoutManager;
    SmsRecycle_Adpter sms_recycleradpter;

    private void InitRecyclerView() {
        //创建现行LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        //设置LayoutMananger
        sms_recyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        sms_recyclerView.setItemAnimator(new DefaultItemAnimator());
        sms_recycleradpter = new SmsRecycle_Adpter(this, senderNumber);
        sms_recyclerView.setAdapter(sms_recycleradpter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //在这里判断是否处于前台还是后台

        if (AppRunStateUtils.GetInsta().isRunningForeground(this)) {
            System.out.println("目前App处于前台  不需要显示window dialog");
        } else {
            InitMyView(myView);
            if (flag) {
                flag = false;
                wManager.addView(myView, mParams);//添加窗口
            } else {
                //直接更新RecyclerView  不确定是否需要更新Windmanger
                if (sms_recycleradpter != null) {
                    sms_recycleradpter.notifyDataSetChanged();
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        if (myView.getParent() != null && wManager != null) {
            wManager.removeView(myView);//移除窗口
            flag = true;
        }

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.close:
                CloseWindow();
                break;
            case R.id.ll_open_sms:
                // Calling startActivity() from outside of an Activity  context requires the
                // FLAG_ACTIVITY_NEW_TASK flag
                Intent intent = new Intent(WindowService.this, MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                CloseWindow();
                break;
        }
    }

    public void CloseWindow() {
        if (wManager != null && myView != null) {
            wManager.removeView(myView);
            flag = true;
        }

    }
}
