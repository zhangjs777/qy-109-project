package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.redis.RedisService;
import com.aaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;

import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @program: springcloud-zjs-0708-project
 * @description:用户管理
 * @author: 张竞赛
 * @create: 2020-07-16 11:33
 **/
@RestController
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Override
    public BaseService getBaseService() {
        return userService;
    }


    /**
    * @Author: js.zhang
    * @Description: 新增
    * @DateTime: 2020/7/16 19:34
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/addUser")
    ResultData addUser(@RequestBody Map map){
        ResultData addResult = super.add(map);
        return addResult;
    }

    /**
    * @Author: js.zhang
    * @Description: 修改
    * @DateTime: 2020/7/16 19:35
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/updateUser")
    ResultData updateUser(@RequestBody Map map ){
        ResultData updateResult = super.update(map);
        return updateResult;
    }


    /**
    * @Author: js.zhang
    * @Description: 批量逻辑删除 status
    * @DateTime: 2020/7/16 19:35
    * @Params: [map, ids]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/updateUserStatus")
    ResultData updateUserStatus(@RequestBody Map map){
        map.get("words");
        ResultData updateResult = super.updateStatus(map);
        return updateResult;
    }


    /**
    * @Author: js.zhang
    * @Description: 查询全部用户 测试用
    * @DateTime: 2020/7/17 20:05
    * @Params: []
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selectUser")
    public ResultData selectUser(){
        Map<String, Object> stringObjectMap = userService.selectAll();
        if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationSuccess(stringObjectMap);
        }else if (SELECT_DATA_FAILED.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationFailed();
        }else{
            return super.operationFailed(DATA_NOT_EXIST.getMsg());
        }
    }





    /**
    * @Author: js.zhang
    * @Description:分页 条件 查询
    * @DateTime: 2020/7/16 21:45
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selectAllUser")
    public ResultData selectAllUser(@RequestBody HashMap hashMap ){
        Map<String, Object> stringObjectMap = userService.selectAllUser(hashMap,redisService);

        if (SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
          return super.operationSuccess(stringObjectMap);
       }else if (FAILED.getCode().equals(stringObjectMap.get(CODE))){
           return super.operationFailed();
       }else{
           return super.operationFailed(DATA_NOT_EXIST.getMsg());
       }

    }





}