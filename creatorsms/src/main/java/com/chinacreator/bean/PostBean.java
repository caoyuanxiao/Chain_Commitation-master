package com.chinacreator.bean;

/**
 * Created by Smile on 2017/5/1.
 */

public class PostBean {

    public static int count = 0;

    public String cityname;
    public int format;
    public String key;

    public PostBean(String cityname, int format, String key) {
        this.cityname = cityname;
        this.format = format;
        this.key = key;
    }

    @Override
    public String toString() {
        return "PostBean{" +
                "cityname='" + cityname + '\'' +
                ", format=" + format +
                ", key='" + key + '\'' +
                '}';
    }
}
