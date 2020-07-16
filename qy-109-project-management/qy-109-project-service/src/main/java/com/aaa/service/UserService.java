package com.aaa.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.mapper.UserMapper;
import com.aaa.model.User;
import com.aaa.redis.RedisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.aaa.status.AddStatus.*;
import static com.aaa.staticproperties.RedisProperties.*;

import static com.aaa.status.LoginStatus.*;

import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.UpdateStatus.*;


/**
 * @program: springcloud-zjs-0708-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-15 21:50
 **/
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;



    /**
     * @Author: js.zhang
     * @Description: 新增用户
     * @DateTime: 2020/7/16 16:46
     * @Params: [redisService, user]
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String,Object> addUser(User user){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //判断用户信息是否为空
        if(user!=null){
            user.setCreateTime(DateUtil.now());
            //执行新增
            Integer addResult = super.add(user);
            if (addResult>0){
                resultMap.put(CODE, ADD_DATA_SUCCESS.getCode());
                resultMap.put(MSG, ADD_DATA_SUCCESS.getMsg());
                return resultMap;
            }else {
                //新增失败
                resultMap.put(CODE,  ADD_DATA_FAILED.getCode());
                resultMap.put(MSG,  ADD_DATA_FAILED.getMsg());
                return resultMap;
            }

        }
        //用户信息为空
            resultMap.put(CODE,  ADD_DATA_NULL.getCode());
            resultMap.put(MSG,   ADD_DATA_NULL.getMsg());
            return resultMap;
    }



    /**
    * @Author: js.zhang
    * @Description: 修改用户
    * @DateTime: 2020/7/16 18:10
    * @Params: [user]
    * @Return java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> updateUser(User user){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断用户信息是否为空
        if(user!=null){
            user.setModifyTime(DateUtil.now());
            //执行修改
            Integer updateResult = super.update(user);
            if (updateResult>0){
                resultMap.put(CODE, UPDATE_DATA_SUCCESS.getCode());
                resultMap.put(MSG,UPDATE_DATA_SUCCESS.getMsg());
                return resultMap;
            }else {
                resultMap.put(CODE,  UPDATE_DATA_FAILED.getCode());
                resultMap.put(MSG,  UPDATE_DATA_FAILED.getMsg());
                return resultMap;
            }

        }
        //用户信息为空系统异常
        resultMap.put(CODE,  UPDATE_DATA_NULL.getCode());
        resultMap.put(MSG,   UPDATE_DATA_NULL.getMsg());
        return resultMap;
    }


    /**
    * @Author: js.zhang
    * @Description: 逻辑删除 批量化
    * @DateTime: 2020/7/16 18:37
    * @Params: [user]
    * @Return java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> delectUser(User user,Integer [] ids){

        Map<String, Object> resultMap = new HashMap<String, Object>();
            //判断ids是否有数据
        if (ids.length>0){
            Integer integer = super.batchUpdate(user, ids);
            if (integer>0){
                resultMap.put(CODE,UPDATE_DATA_SUCCESS.getCode());
                resultMap.put(MSG,UPDATE_DATA_SUCCESS.getMsg());
                return resultMap;
            }else  {
                //修改失败
                resultMap.put(CODE,UPDATE_DATA_FAILED.getCode());
                resultMap.put(MSG,UPDATE_DATA_FAILED.getMsg());
                return resultMap;
            }

        }
        //用户信息为空系统异常
        resultMap.put(CODE,  UPDATE_DATA_NULL.getCode());
        resultMap.put(MSG,   UPDATE_DATA_NULL.getMsg());
        return resultMap;






    }



    /**
    * @Author: js.zhang
    * @Description: 查询
    * @DateTime: 2020/7/16 10:18
    * @Params: [hashMap, redisService]
    * @Return java.util.Map<java.lang.String,java.lang.Object>
    */
    
    public Map<String,Object> selectAllUser(RedisService redisService,Integer pageNo, Integer pageSize, String orderByFiled, String orderWord, String... filds){

        Map<String, Object> resultMap=new HashMap<String, Object>();
        //获取当前用户的token令牌
        String tokenval=redisService.getOne("tokenId").toString();
        //检测token
        if(null==tokenval){
            resultMap.put(CODE,LOGIN_TIMEOUT_EXIT.getCode());
            resultMap.put(MSG,LOGIN_TIMEOUT_EXIT.getMsg());
            return resultMap;
        }


        //判断分页是否为空
        if (pageNo!=null &&pageSize!=null){
            List<User> userList = super.selectByFileds(pageNo, pageSize, null, orderByFiled, orderWord, filds);
            //判断查询数据是否为空
            if (null != userList && userList.size()>0){
                resultMap.put(CODE,SUCCESS.getCode());
                resultMap.put(MSG,SUCCESS.getMsg());
                resultMap.put(DATA,userList);
            }else{
                resultMap.put("code", FAILED.getCode());
                resultMap.put("msg", FAILED.getMsg());
            }
            return resultMap;

        }else {
            //不分页查询
            List<User> users = super.selectListByFiled(null, orderByFiled, filds);
            //判断是否为空
            if (null != users && users.size()>0){
                resultMap.put(CODE,SUCCESS.getCode());
                resultMap.put(MSG,SUCCESS.getMsg());
                resultMap.put(DATA,users);
            }else{
                resultMap.put("code", FAILED.getCode());
                resultMap.put("msg", FAILED.getMsg());
            }
            return resultMap;
        }

    }

}
