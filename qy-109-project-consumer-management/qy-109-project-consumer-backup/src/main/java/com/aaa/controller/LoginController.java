package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-23 17:19
 **/
public class LoginController extends BaseController {
    @Autowired
    private IProjectService iProjectService;
   /**
   * @Author: js.zhang
   * @Description: 登录方法
   * @DateTime: 2020/7/23 17:26
   * @Params: [user]
   * @Return com.aaa.base.ResultData
   */
    @PostMapping("/doLogin")
    public ResultData doLogin(@RequestBody User user){
        return iProjectService.doLogin(user);

    }



}
