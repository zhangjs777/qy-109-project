package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.User;
import com.aaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-09 18:39
 **/
@RestController
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;
    @Override
    public BaseService<User> getBaseService() {
        return userService;
    }
}
