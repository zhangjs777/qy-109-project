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

/**
 * @program: springcloud-zjs-0708-project
 * @description:
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
    ResultData updateUserStatus(@RequestBody Map map,@RequestParam("ids[]") Integer [] ids){
        ResultData updateResult = super.updateStatus(map,ids);
        return updateResult;
    }



    /**
    * @Author: js.zhang
    * @Description: 分页 条件 查询   User
    * @DateTime: 2020/7/16 14:56
    * @Params: [hashMap]
    * @Return com.aaa.zjs.base.ResultData
    */
    @RequestMapping("/selectAllUser")
    ResultData selectAllUser(@RequestBody HashMap hashMap){

        //转换数据
        Integer pageNo = (Integer) hashMap.get("pageNo");
        Integer pageSize = (Integer) hashMap.get("pageSize");
        String orderByFiled = hashMap.get("orderByFiled").toString();
        String orderWord = hashMap.get("orderWord").toString();
        String username = hashMap.get("username").toString();
        String deptid = hashMap.get("deptid").toString();


        //调用查询方法
        Map<String, Object> stringObjectMap = userService.selectAllUser(redisService, pageNo, pageSize, orderByFiled, orderWord, username, deptid);
        //判断返回值
        if (SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationSuccess(stringObjectMap);
        }else if (FAILED.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationFailed();
        }else{
            return super.operationFailed(DATA_NOT_EXIST.getMsg());
        }

    }
}