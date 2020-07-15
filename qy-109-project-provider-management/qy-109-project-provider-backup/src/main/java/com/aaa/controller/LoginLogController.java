package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.LoginLog;
import com.aaa.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company
 * @Author
 * @Date Create in 2020/7/15 12:05
 * @Description
 **/
@RestController
public class LoginLogController extends CommonController<LoginLog> {

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }

    /**
     * @author
     * @description
     *      保存日志
     * @param [loginLog]
     * @date 2020/7/15
     * @return java.lang.Integer
     * @throws
    **/
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog) {
        return getBaseService().add(loginLog);
    }


}
