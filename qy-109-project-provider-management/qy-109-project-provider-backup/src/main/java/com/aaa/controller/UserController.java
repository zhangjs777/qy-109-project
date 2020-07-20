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
        /**
        * @Author: js.zhang
        * @Description: 模糊查询
        * @DateTime: 2020/7/20 18:37
        * @Params: [user]
        * @Return com.aaa.base.ResultData
        */
    @PostMapping("/selectUserAll")
    public ResultData selectUserAll(@RequestBody User user){
        //获取pageNo和pageSize
        Integer pageNo = user.getPageNo();
        Integer pageSize = user.getPageSize();
        //判断是否需要分页
        if (pageNo!=null&&pageSize!=null){
            PageHelper.startPage(pageNo,pageSize);
            List<User> userList = userService.selectUserAll(user);
            //查询出结果后分页
            PageInfo<User> userPageInfo = new PageInfo<User>(userList);
            if (userPageInfo!=null&&userPageInfo.getSize()>0){
                return  super.operationSuccess(userPageInfo);
            }else {
                return  super.operationFailed();
            }
        }else {
            //不分页
            List<User> userList = userService.selectUserAll(user);
            if (userList !=null&&userList.size()>0){
                return  super.operationSuccess(userList);
            }else {
                return  super.operationFailed();
            }

        }


    }





}