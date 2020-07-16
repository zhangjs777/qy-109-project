package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.LoginLog;
import com.aaa.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @Company
 * @Author
 * @Date Create in 2020/7/15 9:30
 * @Description
 **/
@FeignClient(value = "sys-interface")
public interface IProjectService {

    /**
     * @author
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/7/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody User user);

    /**
     * @author
     * @description
     *      新增日志
     * @param [loginLog]
     * @date 2020/7/15
     * @return java.lang.Integer
     * @throws
    **/
    @PostMapping("/addLoginLog")
    Integer addLoginLog(@RequestBody LoginLog loginLog);
    /**
    * @Author: js.zhang
    * @Description: 查询全部user
    * @DateTime: 2020/7/16 15:14
    * @Params: [hashMap]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selectAllUser")
    ResultData selectAllUser(@RequestBody HashMap hashMap);

    @RequestMapping("/addUser")
    ResultData addUser(@RequestBody Map map);

    @RequestMapping("/updateUser")
    ResultData updateUser(@RequestBody Map map );
    @RequestMapping("/updateUserStatus")
    ResultData updateUserStatus(@RequestBody Map map,@RequestParam("ids[]") Integer [] ids);

}
