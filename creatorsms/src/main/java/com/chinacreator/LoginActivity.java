package com.chinacreator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Smile on 2017/5/18.
 */

public class LoginActivity extends AppCompatActivity {
    Button login_btn;
    EditText accountEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login_btn = (Button) findViewById(R.id.login_btn);
        accountEdit = (EditText) findViewById(R.id.login_inputPhone);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountEdit.setError("请输入OA账号或者手机号码!");

            }
        });

    }
}
