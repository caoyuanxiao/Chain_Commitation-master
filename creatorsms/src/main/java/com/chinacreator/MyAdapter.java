package com.chinacreator;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

/**
 * Created by Smile on 2017/4/3.
 */

class MyAdapter extends CursorAdapter implements Filterable {

    ContentResolver resolver;
    public MyAdapter(Context context, Cursor c, int flag) {
        super(context, c,flag);
        resolver = context.getContentResolver();// 初始化ContentResolver

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context,R.layout.item_newmessgae,null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_name_actv = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_number_actv = (TextView) view.findViewById(R.id.tv_phone);
        tv_name_actv.setText(cursor.getString(cursor.getColumnIndex("display_name")));
        tv_number_actv.setText(cursor.getString(cursor.getColumnIndex("data1")));

    }
    /**
     * 该方法在点击AutoCompleteTextView中弹出的listView时，将返回值设置给 activity
     */
    public CharSequence convertToString(Cursor cursor) {
        return cursor.getString(1)+"<"+cursor.getString(2)+">";
    }

}
