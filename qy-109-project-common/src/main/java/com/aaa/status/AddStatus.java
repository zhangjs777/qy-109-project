package com.aaa.status;

/**
 * @Author guohang
 * @Description 添加操作的状态码
 * @Date 2020/5/12 23:00
 */
public enum AddStatus {
    ADD_DATA_SUCCESS("21001", "添加数据成功"),
    ADD_DATA_FAILED("11001", "添加数据失败"),
    ADD_DATA_EXIST("11002","该数据已存在，请修改后重试！"),
    SYS_EROOE("50000","系统异常"),
    ADD_DATA_NULL("11000","新增数据为空"),


    TEST("11111", "测试一下");

    AddStatus(String code, String msg) {
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
