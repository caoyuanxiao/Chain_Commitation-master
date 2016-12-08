package com.example.sms_permiss.bean;

/**
 * Created by Smile on 2016/11/21.
 */

public class Sms_Info {
    int read;

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    private String sms_time;
    private String sms_content;
    private String send_tel;

    public String getSend_tel() {
        return send_tel;
    }

    public void setSend_tel(String send_tel) {
        this.send_tel = send_tel;
    }

    public String getSms_time() {
        return sms_time;
    }

    public void setSms_time(String sms_time) {
        this.sms_time = sms_time;
    }

    public String getSms_content() {
        return sms_content;
    }

    public void setSms_content(String sms_content) {
        this.sms_content = sms_content;
    }

    @Override
    public String toString() {
        return "Sms_Info{" +
                "sms_time='" + sms_time + '\'' +
                ", sms_content='" + sms_content + '\'' +
                ", send_tel='" + send_tel + '\'' +
                '}';
    }

    public Sms_Info(){}
    public Sms_Info(int read, String sms_time, String sms_content, String send_tel) {
        this.read = read;
        this.sms_time = sms_time;
        this.sms_content = sms_content;
        this.send_tel = send_tel;
    }
}
