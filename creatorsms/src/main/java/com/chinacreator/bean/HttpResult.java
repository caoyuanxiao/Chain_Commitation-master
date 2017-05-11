package com.chinacreator.bean;

/**
 * Created by kc on 17/5/11.
 */

public class HttpResult<T> {
    private String resultcode;
    private String reason;
    private int error_code;
    private T result_message;
}
