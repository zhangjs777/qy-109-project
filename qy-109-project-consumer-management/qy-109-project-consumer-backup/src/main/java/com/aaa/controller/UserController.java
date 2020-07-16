package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springcloud-zjs-0708-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-16 09:34
 **/
@RestController
public class UserController {
    @Autowired
    private IProjectService iProjectService;


    /**
    * @Author: js.zhang
    * @Description: 用户表增删改查
    * @DateTime: 2020/7/16 19:42
    * @Params: [hashMap]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selectAllUser")
    ResultData selectAllUser(@RequestBody HashMap hashMap){
      return   iProjectService.selectAllUser(hashMap);
    }


    @RequestMapping("/addUser")
    ResultData addUser(@RequestBody Map map){
        return iProjectService.addUser(map);
    }

    @RequestMapping("/updateUser")
    ResultData updateUser(@RequestBody Map map ){
        return iProjectService.updateUser(map);
    }
    @RequestMapping("/updateUserStatus")
    ResultData updateUserStatus(@RequestBody Map map,@RequestParam("ids[]") Integer [] ids){
        return iProjectService.updateUserStatus(map,ids);
    }



}
