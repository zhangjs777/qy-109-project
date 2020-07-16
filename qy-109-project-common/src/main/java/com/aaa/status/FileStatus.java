package com.aaa.status;

/**
 * @Author guohang
 * @Description
 * @Date 2020/5/29 16:45
 */
public enum FileStatus {

    UPLOAD_SUCCESS("25001", "上传成功"),
    DOWNLOAD_SUCCESS("25001", "下载成功"),
    UPLOAD_FAILED("15001", "上传失败"),
    DOWNLOAD_FAILED("15001", "下载失败"),



    TEST("11111", "测试一下");


    FileStatus(String code, String msg) {
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



