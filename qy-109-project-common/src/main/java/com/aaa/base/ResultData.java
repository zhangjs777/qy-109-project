package com.aaa.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛   xfc
 * @create: 2020-07-08 15:24
 **/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private String detail;
    private T data;


}
