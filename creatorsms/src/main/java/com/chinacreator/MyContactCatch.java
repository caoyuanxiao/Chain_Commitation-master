package com.chinacreator;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.chinacreator.bean.MyPhoneContact;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smile on 2017/4/7.
 */

public class MyContactCatch {
    HashMap<String, MyPhoneContact> contactInfo;
    private static MyContactCatch mCatch;

    public static MyContactCatch getInstance() {
        if (mCatch == null) {
            mCatch = new MyContactCatch();
        }
        return mCatch;
    }

    private MyContactCatch() {

    }

    public void init(Context mContext) {
        System.out.println("开始查询联系人");
        contactInfo = getContactInfo(mContext);
        queryContactPhoneNumber(mContext, contactInfo);
        System.out.println("结束查询联系人");

    }

    public static HashMap<String, MyPhoneContact> mPhoneContact;

    private HashMap<String, MyPhoneContact> queryContactPhoneNumber(Context context, HashMap<String, MyPhoneContact> mContactInfo) {
        mPhoneContact = new HashMap<>();
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
        Cursor cursor = context.getContentResolver().query(phoneUri, cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            MyPhoneContact mMyPhoneContact = new MyPhoneContact();
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int ContactidFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);

            mMyPhoneContact.contactId = cursor.getString(ContactidFieldColumnIndex);
            mMyPhoneContact.phoneNumber = cursor.getString(numberFieldColumnIndex);
            mMyPhoneContact.displayName = cursor.getString(nameFieldColumnIndex);
            for (Map.Entry enter : mContactInfo.entrySet()
                    ) {
                if (enter.getKey().equals(mMyPhoneContact.contactId)) {
                    MyPhoneContact myPhoneContact = (MyPhoneContact) enter.getValue();
                    mMyPhoneContact.photo_thumb_uri = myPhoneContact.photo_thumb_uri;
                }
            }
            mPhoneContact.put(mMyPhoneContact.phoneNumber, mMyPhoneContact);
        }
        return mPhoneContact;
    }

    private Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    private HashMap<String, MyPhoneContact> getContactInfo(Context context) {
        Cursor contactsCursor = context.getContentResolver().query(contactsUri, new String[]{"_id", "display_name", "photo_thumb_uri", "has_phone_number"}, null, null, null);
        HashMap<String, MyPhoneContact> stringContactHashMap = parseCursor(contactsCursor);
        return stringContactHashMap;

    }

    private HashMap<String, MyPhoneContact> parseCursor(Cursor contactsCursor) {
        HashMap<String, MyPhoneContact> mContacts = new HashMap<>();
        while (contactsCursor.moveToNext()) {
            MyPhoneContact myPhoneContact = new MyPhoneContact();
//          获取联系人的id
            myPhoneContact.contactId = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts._ID));
            myPhoneContact.displayName = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            myPhoneContact.photo_thumb_uri = contactsCursor.getString(contactsCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
            if (!TextUtils.isEmpty(myPhoneContact.photo_thumb_uri)) {
                mContacts.put(myPhoneContact.contactId, myPhoneContact);
            }
        }
        return mContacts;

    }
}
