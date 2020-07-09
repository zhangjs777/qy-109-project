package com.aaa.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: springcloud-zjs-0708-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-08 17:04
 **/
@Data
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
}
