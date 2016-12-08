package com.example.test_stutabar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Smile on 2016/11/23.
 */

public class Second_Activity extends AppCompatActivity {

    @BindView(R.id.second_recyclerview)
    RecyclerView mSecondRecyclerview;
    private SystemBarTintManager mTintManager;

    List<String> List_Item;
    List<Integer> List_Type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_second);
        ButterKnife.bind(this);
        initWindow();
        Initdate();
        mSecondRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mSecondRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mSecondRecyclerview.setAdapter(new MyAdapter(this,List_Item,List_Type));



    }

    private void Initdate() {
        List_Item=new ArrayList<>();
        List_Type=new ArrayList<>();
        for (int i=0;i<30;i++){
            List_Item.add(i+"yuanxiao");
            if (i%3==0){
                List_Type.add(2);
            }else{
                List_Type.add(1);
            }
        }
    }

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
}
