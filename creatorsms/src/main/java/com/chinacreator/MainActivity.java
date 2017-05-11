package com.chinacreator;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chinacreator.bean.MyPhoneContact;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import static com.chinacreator.R.id.tv_sim;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TelephonyManager mTelephonyManager;
    Class<TelephonyManager> clz;


    Menu menu;
    Button btn_menu;
    HashMap<String, MyPhoneContact> contactInfo;
    Button btn1, btn2;
    TextView tv_sim;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("启动onCreate");

        btn1 = (Button) findViewById(R.id.send_1);
        btn2 = (Button) findViewById(R.id.send_2);

        findViewById(R.id.send_sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDounble = initQualcommDoubleSim(MainActivity.this);
                int simid = 1;
                if (!isDounble) {
                    simid = 0;
                }
                SimUtil.sendSMS(MainActivity.this, simid, "17673045633", null, "你好我是测试", null, null);
            }
        });
        // getAddress();
        // queryContactPhoneNumber();
        mPlayer = (TextView) findViewById(R.id.tv_play);
        tv_sim = (TextView) findViewById(R.id.tv_sim);


        //SimUtil.sendSMS(this, 0, "13337227273", null, "你好我是测试", null, null);
        // sendSms(1);
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String number = manager.getLine1Number();
        TextView tv_number = (TextView) findViewById(R.id.tv_number);
//        if (!TextUtils.isEmpty(number)) {
//            tv_number.setText(number);
//        } else {
            //  tv_number.setText("无法查询到号码");
            Uri uri = Uri.parse("content://telephony/siminfo");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    number = cursor.getString(cursor.getColumnIndexOrThrow("number"));
                }
            }
            tv_number.setText(number);
//        }

        if (Build.VERSION.SDK_INT >= 21) {
            initSmsManager();
        } else {
            //boolean isDouble=  initQualcommDoubleSim(this);
            // SimUtil.sendSMS(this, 1, "17673045633", null, "你好我是测试", null, null);
        }


        GetPermission();

        // List<MyPhoneContact> contacts = printContacts();


      /*  for (MyPhoneContact mContact : contacts
                ) {
            System.out.println("时间:" + mContact.toString());
        }*/
       /* List<String> mList = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            mList.add("元宵" +i);
        }
        testAddContact(mList);*/


        // getContactInfo();

        /*MultiAutoCompleteTextView autoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.actv);
        //AutoCompleteTextView的设置
        String[] colmuns = new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_THUMBNAIL_URI};
        Cursor cursor = getContentResolver().query(contactsUri, colmuns,
                null, null, null);
        //  MyAdapter myAdapter = new MyAdapter(this, cursor, 0,contacts);
        MyContactAdapter myAdapter = new MyContactAdapter(this, contacts);
        //此回调返回一个cursor，然后把次cursor设置给CursorAdapter
        autoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        autoCompleteTextView.setAdapter(myAdapter);*/



        /*myAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence charSequence) {
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"_id", "data1", "display_name"}, "data1 like '%" + charSequence + "%'", null, null);
                //Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"_id", "data1", "display_name"}, "display_name like '%" + charSequence + "%'", null, null);
                return cursor;
            }
        });*/


        // List<MyPhoneContact> contacts = printContacts();

       /* for (MyPhoneContact mContact : contacts
                ) {
            System.out.println("mContacted:" + mContact.toString());
        }*/
        //getContactInfo();

       /* ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                1);
*/
//        btn_menu = (Button) findViewById(R.id.btn_popumenu);
//
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               /* PopupMenu popupMenu = new PopupMenu(MainActivity.this, btn_menu);
//                // 通过代码添加菜单项
//                menu = popupMenu.getMenu();
//                menu.add(Menu.NONE, Menu.FIRST + 0, 0, "复制");
//                menu.add(Menu.NONE, Menu.FIRST + 1, 1, "粘贴");
//
//                // 通过XML文件添加菜单项
//                MenuInflater menuInflater = getMenuInflater();
//                menuInflater.inflate(R.menu.qkreply, menu);
//                popupMenu.show();*/
//
//                SelectDialog selectDialog = new SelectDialog(MainActivity.this, R.style.dialog);//创建Dialog并设置样式主题
//                Window win = selectDialog.getWindow();
//                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//                params.x = -80;//设置x坐标
//                params.y = -60;//设置y坐标
//                win.setAttributes(params);
//                selectDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
//                selectDialog.show();
//            }
//        });





/*
        printTelephonyManagerMethodNamesForThisDevice(this);
        TelephonyInfo.getInstance().initConfig(this);
        String imeiSIM1 = TelephonyInfo.getInstance().getImeiSIM1();
        Log.i(TAG, "onCreate: SIM1：" + imeiSIM1);
        boolean b = TelephonyInfo.getInstance().hasSimByUsing();
        Log.i(TAG, "onCreate: 有卡在使用" + b);

        String url = "content://telephony/siminfo";
        Uri parse = Uri.parse(url);
        Cursor query = getContentResolver().query(parse, null, null, null, null);
        while (query.moveToNext()) {
            long sim_id = query.getLong(query.getColumnIndex("sim_id"));
            if (sim_id != -1) {
                String number = query.getString(query.getColumnIndex("number"));
                String name = query.getString(query.getColumnIndex("carrier_name"));
                System.out.println("最大的sim_id卡槽数量为：" + sim_id + "当前的号码为：" + number + "运营商：" + name);
            }

        }
        mTelephonyManager = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        clz = (Class<TelephonyManager>) mTelephonyManager.getClass();
        //这里可以

*/
        //sendSms(1);
      /*  telephonyInfo= CTelephoneInfo.getInstance(this);
        telephonyInfo.setCTelephoneInfo();
        String imeiSIM1 = telephonyInfo.getImeiSIM1();
        String imeiSIM2 = telephonyInfo.getImeiSIM2();
        String iNumeric1 = telephonyInfo.getINumeric1();
        String iNumeric2 = telephonyInfo.getINumeric2();
        boolean network1 = telephonyInfo.isDataConnected1();
        boolean network2 = telephonyInfo.isDataConnected2();
        boolean isSIM1Ready = telephonyInfo.isSIM1Ready();
        boolean isSIM2Ready = telephonyInfo.isSIM2Ready();
        boolean isDualSIM = telephonyInfo.isDualSim();
        mPlayer.setText(" IME1 : " + imeiSIM1 + "\n" +
                " IME2 : " + imeiSIM2 + "\n" +
                " network1 : " + network1 + "\n" +
                " network2 : " + network2 + "\n" +
                " iNumeric1 : " + iNumeric1 + "\n" +
                " iNumeric2 : " + iNumeric2 + "\n" +
                " IS DUAL SIM : " + isDualSIM + "\n" +
                " IS SIM1 READY : " + isSIM1Ready + "\n" +
                " IS SIM2 READY : " + isSIM2Ready + "\n");*/


    }

    //    public void reflect(){
//        Class<?> smsManagerClass = null;
//        Class[] divideMessagePamas = { String.class };
//        Class[] sendMultipartTextMessagePamas = { String.class,String.class, ArrayList.class, ArrayList.class,ArrayList.class, int.class };
//        Method divideMessage = null;
//        Method sendMultipartTextMessage = null;
//        try {
//            smsManagerClass = Class.forName("android.telephony.SmsManager");
//            Method method = smsManagerClass.getMethod("getDefault", new Class[]{});
//            Object smsManager = method.invoke(smsManagerClass, new Object[]{});
//            divideMessage = smsManagerClass.getMethod("divideMessage",divideMessagePamas);
//            sendMultipartTextMessage = smsManagerClass.getMethod("sendMultipartTextMessage", sendMultipartTextMessagePamas);
//            ArrayList<String> magArray = (ArrayList<String>) divideMessage.invoke(smsManager, msg);
//            sendMultipartTextMessage.invoke(smsManager,phoneNumber, "", magArray, null, null, phoneType);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    TextView mPlayer;
    CTelephoneInfo telephonyInfo;

    public static final int REQUEST_CODE_ASK_CALL_PHONE = 1;

    private void GetPermission() {
        if (Build.VERSION.SDK_INT >= 23) {

            int phone_state = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
            int sendsms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            if (sendsms == PackageManager.PERMISSION_GRANTED && phone_state == PackageManager.PERMISSION_GRANTED) {
                //有发送短信权限
                initSmsManager();
            } else {
                //无发送短信权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_ASK_CALL_PHONE);
            }


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void initSmsManager() {
        final SubscriptionManager sManager = (SubscriptionManager) getSystemService(Context
                .TELEPHONY_SUBSCRIPTION_SERVICE);
        List<SubscriptionInfo> list = sManager.getActiveSubscriptionInfoList();
        mPlayer.setText("当前的卡数量为：" + list.size());
        if (list.size() == 1) {
            btn1.setVisibility(View.VISIBLE);
            SubscriptionInfo sInfo = list.get(0);
            String provider = (String) sInfo.getCarrierName();
            btn1.setText(provider);
        } else if (list.size() == 2) {
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);

            SubscriptionInfo sInfo = list.get(0);
            String provider = (String) sInfo.getCarrierName();
            btn1.setText(provider);

            SubscriptionInfo sInfo1 = list.get(1);
            String provider1 = (String) sInfo1.getCarrierName();
            btn2.setText(provider1);
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    sendSms(0);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    sendSms(1);
                }
            }
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            SubscriptionInfo sInfo = list.get(i);
            //String provider = (String) sInfo.getCarrierName();
            String number = String.valueOf(sInfo.getMcc()) + "0"
                    + String.valueOf(sInfo.getMnc());
            String provider = matchIMSI(number);
//            sb.append("Number:" + number);
            sb.append("卡：" + i + "运营商：" + sInfo.getDisplayName() + "\n" + "电话号码为：" + sInfo.getNumber());
        }

        tv_sim.setText(sb.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("启动onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("启动onPause");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                initSmsManager();
                break;
        }
    }


    private Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    private HashMap<String, MyPhoneContact> getContactInfo() {
        System.out.println("开始时间:");
        Cursor contactsCursor = getContentResolver().query(contactsUri, new String[]{"_id", "display_name", "photo_thumb_uri", "has_phone_number"}, null, null, null);
        HashMap<String, MyPhoneContact> stringContactHashMap = parseCursor(contactsCursor);
        System.out.println("结束时间:");
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
            //  int hasphonenumber = contactsCursor.getInt(contactsCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

//          根据联系人的_id(此处获取的_id和raw_contact_id相同，因为contacts表就是通过这两个字段和raw_contacts表取得联系)查询电话和email

//            if (hasphonenumber > 0) {
////              查询电话
//                Cursor phoneCursor = getContentResolver().query(phoneUri, new String[]{ContactsContract.CommonDataKinds.Phone.DATA1}, "raw_contact_id = ?", new String[]{myPhoneContact.contactId + ""}, null);
//                if (phoneCursor.moveToFirst()) {
//                    do {
//                        //遍历联系人下面所有的电话号码
//                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        myPhoneContact.phoneNumber = phoneNumber;
//                        //使用Toast技术显示获得的号码
//                        System.out.println("多个号码：" + phoneNumber);
//                        mContacts.put(phoneNumber, myPhoneContact);
//                    } while (phoneCursor.moveToNext());
//                }
//                //  map.put("phone", phone);
//                phoneCursor.close();

            // }
            if (!TextUtils.isEmpty(myPhoneContact.photo_thumb_uri)) {
                mContacts.put(myPhoneContact.contactId, myPhoneContact);
            }
        }
        System.out.println("时间:查询到的数据为:" + mContacts.size());
        return mContacts;

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void sendSms(final int which) {
        SubscriptionInfo sInfo = null;

        final SubscriptionManager sManager = (SubscriptionManager) getSystemService(Context
                .TELEPHONY_SUBSCRIPTION_SERVICE);

        List<SubscriptionInfo> list = sManager.getActiveSubscriptionInfoList();
        if (list.size() == 2) {// double card
            sInfo = list.get(which);
        } else {//single card
            sInfo = list.get(0);
        }

        if (sInfo != null) {

        }

        String provider = matchIMSI(String.valueOf(sInfo.getMcc()) + "0"
                + String.valueOf(sInfo.getMnc()));


        int subId = sInfo.getSubscriptionId();
        Log.i(TAG, " select provider = " + provider + ", subid = "
                + subId);
        SmsManager manager = SmsManager
                .getSmsManagerForSubscriptionId(subId);

        manager.getSubscriptionId();


        if (!TextUtils.isEmpty(provider)) {
            //manager.sendTextMessage("15674914601", null, "YE", null, null);
            Toast.makeText(this, "发送短信到：15674914601", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "无法正确的获取SIM卡信息，请稍候重试",
                    Toast.LENGTH_SHORT).show();
        }
    }


    // 获取SIM卡的服务商
    public String matchIMSI(String porvide) {

        if (porvide.equals("46000") || porvide.equals("46002") || porvide.equals("46007") || porvide.equals("46004")) {
            Log.v("", "中国移动");
            return "中国移动";
        } else if (porvide.equals("46001") || porvide.equals("46006") || porvide.equals("46009")) {
            Log.v("", "中国联通");
            return "中国联通";
        } else if (porvide.equals("46003") || porvide.equals("46005") || porvide.equals("46011")) {
            Log.v("", "中国电信");
            return "中国电信";
        } else if (porvide.equals("46020")) {
            return "中国铁通";
        } else {
            return "无法识别";
        }
    }

    public int getSimState(int slotID) {
        int status = 0;
        try {
            Method mtd = clz.getMethod("getSimState", int.class);
            mtd.setAccessible(true);
            status = (Integer) mtd.invoke(mTelephonyManager, slotID);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return status;
    }


    public static void printTelephonyManagerMethodNamesForThisDevice(
            Context context) {

        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass;
        try {
            telephonyClass = Class.forName(telephony.getClass().getName());
            Method[] methods = telephonyClass.getMethods();
            for (int idx = 0; idx < methods.length; idx++) {

                Log.i("电话信息：",
                        "\n" + methods[idx] + " declared by "
                                + methods[idx].getDeclaringClass());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testAddContact(List<String> name) {


        for (String ss : name) {
            //首先插入空值，再得到rawContactsId ，用于下面插值
            ContentValues values = new ContentValues();
            //insert a null value
            Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactsId = ContentUris.parseId(rawContactUri);

            //往刚才的空记录中插入姓名
            values.clear();
            //A reference to the _ID that this data belongs to
            values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, rawContactsId);
            //"CONTENT_ITEM_TYPE" MIME type used when storing this in data table
            values.put(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            //The name that should be used to display the contact.
            values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, ss);
            //insert the real values
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
            //插入电话
            values.clear();
            values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, rawContactsId);
            //String "Data.MIMETYPE":The MIME type of the item represented by this row
            //String "CONTENT_ITEM_TYPE": MIME type used when storing this in data table.
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "1008611");
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }

    }

    public static boolean initMtkDoubleSim(Context mContext) {
        Boolean isMtkDoubleSim = false;

        try {

            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> c = Class.forName("com.android.internal.telephony.Phone");
            Field fields1 = c.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            int simId_1 = (Integer) fields1.get(null);
            Field fields2 = c.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            int simId_2 = (Integer) fields2.get(null);
            Method m = TelephonyManager.class.getDeclaredMethod(
                    "getSubscriberIdGemini", int.class);
            String imsi_1 = (String) m.invoke(tm, simId_1);
            String imsi_2 = (String) m.invoke(tm, simId_2);
            Method m1 = TelephonyManager.class.getDeclaredMethod(
                    "getDeviceIdGemini", int.class);
            String imei_1 = (String) m1.invoke(tm, simId_1);
            String imei_2 = (String) m1.invoke(tm, simId_2);
            Method mx = TelephonyManager.class.getDeclaredMethod(
                    "getPhoneTypeGemini", int.class);
            int phoneType_1 = (Integer) mx.invoke(tm, simId_1);
            int phoneType_2 = (Integer) mx.invoke(tm, simId_2);
            if (TextUtils.isEmpty(imsi_1) && (!TextUtils.isEmpty(imsi_2))) {
                String defaultImsi = imsi_2;
            }
            if (TextUtils.isEmpty(imsi_2) && (!TextUtils.isEmpty(imsi_1))) {
                String defaultImsi = imsi_1;
            }
        } catch (Exception e) {
            isMtkDoubleSim = false;

            return isMtkDoubleSim;
        }
        isMtkDoubleSim = true;
        return isMtkDoubleSim;
    }


    public static boolean initQualcommDoubleSim(Context mContext) {
        boolean isQualcommDoubleSim;
        try {
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> cx = Class
                    .forName("android.telephony.MSimTelephonyManager");
            Object obj = mContext.getSystemService(
                    "phone_msim");
            int simId_1 = 0;
            int simId_2 = 1;

            Method mx = cx.getMethod("getDataState");
// int stateimei_1 = (Integer) mx.invoke(cx.newInstance());
            int stateimei_2 = tm.getDataState();
            Method mde = cx.getMethod("getDefault");
            Method md = cx.getMethod("getDeviceId", int.class);
            Method ms = cx.getMethod("getSubscriberId", int.class);
            Method mp = cx.getMethod("getPhoneType");

// Object obj = mde.invoke(cx);

            String imei_1 = (String) md.invoke(obj, simId_1);
            String imei_2 = (String) md.invoke(obj, simId_2);

            String imsi_1 = (String) ms.invoke(obj, simId_1);
            String imsi_2 = (String) ms.invoke(obj, simId_2);

            int statephoneType_1 = tm.getDataState();
            int statephoneType_2 = (Integer) mx.invoke(obj);
            Log.e("tag", statephoneType_1 + "---" + statephoneType_2);

// Class<?> msc = Class.forName("android.telephony.MSimSmsManager");
// for (Method m : msc.getMethods()) {
// if (m.getName().equals("sendTextMessage")) {
// m.getParameterTypes();
// }
// Log.e("tag", m.getName());
// }

        } catch (Exception e) {
            isQualcommDoubleSim = false;
            return isQualcommDoubleSim;

        }
        isQualcommDoubleSim = true;
        return isQualcommDoubleSim;
    }
}
