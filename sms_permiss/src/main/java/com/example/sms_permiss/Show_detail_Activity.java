package com.example.sms_permiss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sms_permiss.Adapter.ShowDetail_Adapter;
import com.example.sms_permiss.Adapter.SmsRecycle_Adpter;

import org.w3c.dom.Text;

import static android.R.attr.id;
import static android.R.attr.menuCategory;

/**
 * Created by Smile on 2016/11/22.
 */

public class Show_detail_Activity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    ImageView iv_close, iv_showdetail_edit, iv_showdetail_popuwind;
    TextView tv_showdetai_address;
    RelativeLayout rl_showdetail1, rl_showdetail2, rl_showdetail3, rl_showdetailtitle;
    LinearLayout ll_showdetail_buttom, ll_showdetail_edit;
    private int mHeight_buttomlayout;
    private WindowManager mMWindowManager;
    private int mWindWidth;
    private int mWindHeight;
    private LinearLayout.LayoutParams mShowdetailEditLayoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail);
        InitView();
        SetButtomPosition();
        SetRecyclerAdapter();
        SetOnclikListener();
    }

    private void SetButtomPosition() {
        mMWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindHeight = mMWindowManager.getDefaultDisplay().getHeight();
        mWindWidth = mMWindowManager.getDefaultDisplay().getWidth();
        mHeight_buttomlayout = ll_showdetail_buttom.getHeight();
        mShowdetailEditLayoutParams = (LinearLayout.LayoutParams)
                ll_showdetail_edit.getLayoutParams();
        mShowdetailEditLayoutParams.setMargins(0,mWindHeight,0,mHeight_buttomlayout+mWindHeight);
        ll_showdetail_edit.setLayoutParams(mShowdetailEditLayoutParams);

    }

    private void SetRecyclerAdapter() {
        //创建现行LinearLayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //设置LayoutMananger
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ShowDetail_Adapter mShowDetail_adapter = new ShowDetail_Adapter(this);
        mRecyclerView.setAdapter(mShowDetail_adapter);
    }

    private void SetOnclikListener() {
        iv_close.setOnClickListener(this);
        iv_showdetail_edit.setOnClickListener(this);
        iv_showdetail_popuwind.setOnClickListener(this);
    }

    private void InitView() {
        rl_showdetailtitle = (RelativeLayout) findViewById(R.id.rl_showdetailtitle);
        ll_showdetail_edit = (LinearLayout) findViewById(R.id.ll_showdetail_edit);
        mRecyclerView = (RecyclerView) findViewById(R.id.showdetail_recyclerview);
        iv_close = (ImageView) findViewById(R.id.iv_detail_close);
        tv_showdetai_address = (TextView) findViewById(R.id.tv_showdetai_address);
        rl_showdetail1 = (RelativeLayout) findViewById(R.id.rl_showdetail1);
        rl_showdetail2 = (RelativeLayout) findViewById(R.id.rl_showdetail2);
        rl_showdetail3 = (RelativeLayout) findViewById(R.id.rl_showdetail3);
        ll_showdetail_buttom = (LinearLayout) findViewById(R.id.ll_showdetail_buttom);
        iv_showdetail_edit = (ImageView) findViewById(R.id.iv_showdetail_edit);
        iv_showdetail_popuwind = (ImageView) findViewById(R.id.iv_showdetail_popuwind);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_detail_close:
                finish();
                break;
            case R.id.iv_showdetail_popuwind:
                ShowEditButtomLayout();
                break;
            case R.id.rl_showdetail1:
                Toast.makeText(this, "弹出Popuwind", Toast.LENGTH_LONG).show();
                break;
            case R.id.ll_showdetail_edit:

                break;
            case R.id.iv_showdetail_edit:
                ShowPopuWindButtomLayout();
                break;
        }
    }

    //显示底部显示popuwind的布局
    private void ShowPopuWindButtomLayout() {

    }

    //显示底部显示Edittext的布局
    private void ShowEditButtomLayout() {
        Toast.makeText(this,"开启Edit动画",0).show();

        ll_showdetail_buttom.setVisibility(View.GONE);
        TranslateAnimation translateAnimation1=new TranslateAnimation(0,0,mWindHeight,mWindHeight-mHeight_buttomlayout);
        translateAnimation1.setDuration(500);
        translateAnimation1.setFillAfter(true);
        ll_showdetail_edit.setAnimation(translateAnimation1);
        translateAnimation1.startNow();
        /*TranslateAnimation translateAnimation=new TranslateAnimation(0,0,mHeight_buttomlayout,mWindHeight);
        translateAnimation.setDuration(500);
        ll_showdetail_buttom.setAnimation(translateAnimation);
        translateAnimation.startNow();*/

        /*TranslateAnimation translateAnimation1=new TranslateAnimation(0,0,mWindHeight,mHeight_buttomlayout);
        translateAnimation1.setDuration(500);
        ll_showdetail_edit.setAnimation(translateAnimation1);
        translateAnimation1.startNow();*/

    }
}
