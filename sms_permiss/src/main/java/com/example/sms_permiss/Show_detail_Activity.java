package com.example.sms_permiss;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sms_permiss.Adapter.ShowDetail_Adapter;
import com.example.sms_permiss.Utils.WindUtils;

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
        mWindHeight = WindUtils.GetInstance(this).GetWindowHeight();
        mWindWidth = WindUtils.GetInstance(this).GetWindowWidth();
        System.out.println("屏幕的宽度：" + mWindWidth + "屏幕的高度：" + mWindHeight);

        /*mShowdetailEditLayoutParams = (LinearLayout.LayoutParams)
                ll_showdetail_edit.getLayoutParams();
        mShowdetailEditLayoutParams.setMargins(0, mWindHeight, 0, mHeight_buttomlayout );


        ll_showdetail_edit.setLayoutParams(mShowdetailEditLayoutParams);*/
        ViewTreeObserver vto = ll_showdetail_buttom.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                mHeight_buttomlayout = ll_showdetail_buttom.getMeasuredHeight();
                return true;
            }
        });

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
        final ObjectAnimator translationY = ObjectAnimator.ofFloat(ll_showdetail_edit,
                "translationY", 0, mHeight_buttomlayout);
        translationY.setDuration(500);
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                final ObjectAnimator translationY = ObjectAnimator.ofFloat(ll_showdetail_buttom,
                        "translationY",  mHeight_buttomlayout,0);
                translationY.setDuration(500);
                translationY.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        translationY.start();
    }

    //显示底部显示Edittext的布局
    private void ShowEditButtomLayout() {

        final ObjectAnimator translationY = ObjectAnimator.ofFloat(ll_showdetail_buttom,
                "translationY", 0, mHeight_buttomlayout);
        translationY.setDuration(500);
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (ll_showdetail_edit.getVisibility()==View.GONE){
                    ll_showdetail_edit.setVisibility(View.VISIBLE);
                }

                final ObjectAnimator translationY = ObjectAnimator.ofFloat(ll_showdetail_edit,
                        "translationY", mHeight_buttomlayout,0);
                translationY.setDuration(500);
                translationY.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        translationY.start();

    }
}
