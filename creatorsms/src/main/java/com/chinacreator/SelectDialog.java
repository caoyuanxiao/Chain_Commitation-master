package com.chinacreator;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Smile on 2017/4/2.
 */

public class SelectDialog extends AlertDialog {

    public SelectDialog(Context context, int theme) {
        super(context, theme);
    }

    public SelectDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
