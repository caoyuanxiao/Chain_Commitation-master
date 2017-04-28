package com.chinacreator.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.chinacreator.R;

import java.util.List;

/**
 * Created by Smile on 2017/4/3.
 */

public class MyContactAdapter extends BaseAdapter implements Filterable {

    List<MyPhoneContact> mMyPhoneContactList;
    Context mContext;

    public MyContactAdapter(Context context, List<MyPhoneContact> mMyPhoneContacts) {
        mContext = context;
        mMyPhoneContactList = mMyPhoneContacts;
    }

    @Override
    public int getCount() {
        return mMyPhoneContactList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_newmessgae, null);
        TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_name.setText(mMyPhoneContactList.get(position).getDisplayName());
        tv_phone.setText(mMyPhoneContactList.get(position).getPhoneNumber());
        return view;
    }

    @Override
    public Filter getFilter() {
        System.out.println("执行了Filter");
        return null;
    }
}
