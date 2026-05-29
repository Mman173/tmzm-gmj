package com.hg.tmzm.common.response;

import java.io.Serializable;

/**
 * RuoYi-style unified response object
 *
 * @author hg
 */
public class AjaxResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Status code */
    private int code;

    /** Message */
    private String msg;

    /** Data */
    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Return success with no data
     */
    public static AjaxResult success() {
        return new AjaxResult(200, "操作成功");
    }

    /**
     * Return success with data
     */
    public static AjaxResult success(Object data) {
        return new AjaxResult(200, "操作成功", data);
    }

    /**
     * Return success with custom message and data
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(200, msg, data);
    }

    /**
     * Return error with message
     */
    public static AjaxResult error(String msg) {
        return new AjaxResult(500, msg);
    }

    /**
     * Return error with code and message
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg);
    }

    @Override
    public String toString() {
        return "AjaxResult{code=" + code + ", msg='" + msg + "', data=" + data + '}';
    }
}
