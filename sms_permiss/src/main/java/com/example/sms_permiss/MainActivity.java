package com.example.sms_permiss;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Intent intent = new Intent(Intent.ACTION_MAIN);
        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.Settings");
        intent.setComponent(cn);
        intent.putExtra(":android:show_fragment", "com.android.settings.applications.AppOpsSummary");
        startActivity(intent);*/


    }
}
