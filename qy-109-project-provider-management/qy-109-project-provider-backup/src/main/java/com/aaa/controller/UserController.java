package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.redis.RedisService;
import com.aaa.service.UserService;
import com.aaa.utils.BaseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;

import static com.aaa.status.AddStatus.ADD_DATA_SUCCESS;
import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;
import static com.aaa.status.UpdateStatus.UPDATE_DATA_SUCCESS;

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
    @PostMapping("/addUser")
    ResultData addUser(@RequestBody User user){
        Map<String, Object> stringObjectMap = userService.addUser(user);
        if ( ADD_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
            return    super.operationSuccess();
        }
        return  super.operationFailed();
    }

    /**
    * @Author: js.zhang
    * @Description: 修改
    * @DateTime: 2020/7/16 19:35
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/updateUser")
    ResultData updateUser(@RequestBody User user ){
        Map<String, Object> stringObjectMap = userService.updateUser(user);
        if (UPDATE_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
         return    super.operationSuccess();
        }
         return  super.operationFailed();
    }


    /**
    * @Author: js.zhang
    * @Description: 批量删除
    * @DateTime: 2020/7/16 19:35
    * @Params: [map, ids]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/deleteUserById")
    ResultData updateUserStatus(@RequestParam("ids[]") Integer [] ids){
        ResultData resultData = super.batchDelete(ids);
        return resultData;
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
    @PostMapping("/selectAllUser")
    public ResultData selectAllUser(@RequestBody User user ){
        PageInfo<HashMap> hashMapPageInfo = userService.selectAllUser(user);

        if (hashMapPageInfo!=null&&hashMapPageInfo.getSize()>0){
            return  super.operationSuccess(hashMapPageInfo);
        }else {
            return  super.operationFailed();
        }

    }

    /**
    * @Author: js.zhang
    * @Description: 通过id查询user具体信息 部门 管理员
    * @DateTime: 2020/7/22 21:35
    * @Params: [id]
    * @Return com.aaa.base.ResultData
    */
    @GetMapping("/selectUserById")
    public ResultData selectUserById(@RequestParam ("id") Long id){
        Map map = userService.selectUserById(id);
        if (map.isEmpty()){
          return   super.operationFailed();
        }else {
            return super.operationSuccess(map);
        }
    }



        /**
        * @Author: js.zhang
        * @Description: 通过username模糊查询和deptid查询 （弃用）
        * @DateTime: 2020/7/20 18:37
        * @Params: [user]
        * @Return com.aaa.base.ResultData
        */
    @PostMapping("/selectUserAll")
    public ResultData selectUserAll(@RequestBody User user){

        PageInfo<User> userPageInfo = userService.selectUserAll(user);

            if (userPageInfo!=null&&userPageInfo.getSize()>0){
                return  super.operationSuccess(userPageInfo);
            }else {
                return  super.operationFailed();
            }



    }





}