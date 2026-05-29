package com.hg.tmzm.common.response;

/**
 * 登录响应（token 在顶层）
 */
public class LoginResult {

    private int code;
    private String msg;
    private String token;

    public LoginResult(int code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = token;
    }

    public static LoginResult success(String token) {
        return new LoginResult(200, "操作成功", token);
    }

    public static LoginResult error(String msg) {
        return new LoginResult(500, msg, null);
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
