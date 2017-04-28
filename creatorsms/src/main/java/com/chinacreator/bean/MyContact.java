package com.chinacreator.bean;

/**
 * Created by Smile on 2017/4/6.
 */

public class MyContact {


    public String id;
    public String number;
    public String display_name;
    public String contact_id;
    public String contact_presence;
    public String contact_status;
    public String nomal_number;
    public byte[] mAvatarData;

    public MyContact(){

    }

    @Override
    public String toString() {
        return "MyContact{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", display_name='" + display_name + '\'' +
                ", contact_id='" + contact_id + '\'' +
                ", contact_presence='" + contact_presence + '\'' +
                ", contact_status='" + contact_status + '\'' +
                ", nomal_number='" + nomal_number + '\'' +
                '}';
    }
}
