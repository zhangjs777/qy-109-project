package com.aaa.status;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-08 15:49
 **/
public enum LoginStatus {


    LOGIN_SUCCESS("200", "登录成功"),
    LOGIN_FAILED("400", "登录失败，系统异常"),
    USER_EXIST("201", "用户已经存在"),
    USER_NOT_EXIST("401", "用户不存在"),
    PASSWORD_WRONG("402", "密码错误"),
    LOGOUT_WRONG("405", "用户退出异常"),
    LOGIN_TIMEOUT_EXIT("106","超时自动退出，请重新登录"),
    SYSTEM_EXCEPTION("406", "系统异常");
    LoginStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
