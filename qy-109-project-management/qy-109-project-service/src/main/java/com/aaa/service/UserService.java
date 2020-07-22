package com.aaa.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.mapper.UserMapper;
import com.aaa.model.User;
import com.aaa.redis.RedisService;

import com.aaa.utils.BaseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.aaa.status.AddStatus.*;
import static com.aaa.staticproperties.RedisProperties.*;

import static com.aaa.status.LoginStatus.*;

import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.SelectStatus.*;
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
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> addUser(User user) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //判断用户信息是否为空
        if (user != null) {
            user.setCreateTime(DateUtil.now());
            //执行新增
            Integer addResult = super.add(user);
            if (addResult > 0) {
                resultMap.put(CODE, ADD_DATA_SUCCESS.getCode());
                resultMap.put(MSG, ADD_DATA_SUCCESS.getMsg());
                return resultMap;
            } else {
                //新增失败
                resultMap.put(CODE, ADD_DATA_FAILED.getCode());
                resultMap.put(MSG, ADD_DATA_FAILED.getMsg());
                return resultMap;
            }

        }
        //用户信息为空
        resultMap.put(CODE, ADD_DATA_NULL.getCode());
        resultMap.put(MSG, ADD_DATA_NULL.getMsg());
        return resultMap;
    }


    /**
     * @Author: js.zhang
     * @Description: 修改用户
     * @DateTime: 2020/7/16 18:10
     * @Params: [user]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> updateUser(User user) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断用户信息是否为空
        if (user != null) {
            user.setModifyTime(DateUtil.now());
            //执行修改
            Integer updateResult = super.update(user);
            if (updateResult > 0) {
                resultMap.put(CODE, UPDATE_DATA_SUCCESS.getCode());
                resultMap.put(MSG, UPDATE_DATA_SUCCESS.getMsg());
                return resultMap;
            } else {
                resultMap.put(CODE, UPDATE_DATA_FAILED.getCode());
                resultMap.put(MSG, UPDATE_DATA_FAILED.getMsg());
                return resultMap;
            }

        }
        //用户信息为空系统异常
        resultMap.put(CODE, UPDATE_DATA_NULL.getCode());
        resultMap.put(MSG, UPDATE_DATA_NULL.getMsg());
        return resultMap;
    }


    /**
     * @Author: js.zhang
     * @Description: 逻辑删除 批量化
     * @DateTime: 2020/7/16 18:37
     * @Params: [user]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> delectUser(List<Object> ids,User user) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Integer integer = super.batchUpdate(user,ids);
            if (integer > 0) {
                resultMap.put(CODE, UPDATE_DATA_SUCCESS.getCode());
                resultMap.put(MSG, UPDATE_DATA_SUCCESS.getMsg());
                return resultMap;
            } else {
                //修改失败
                resultMap.put(CODE, UPDATE_DATA_FAILED.getCode());
                resultMap.put(MSG, UPDATE_DATA_FAILED.getMsg());
                return resultMap;
            }

    }

    /**
    * @Author: js.zhang
    * @Description: 查询全部
    * @DateTime: 2020/7/17 18:56
    * @Params: []
    * @Return java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> selectAll() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //执行查询方法
        List<User> userList = userMapper.selectAll();
        if (null != userList  && !userList.isEmpty()){
            resultMap.put(CODE,SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG,SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA,userList);
            return resultMap;
        }else{
            resultMap.put(CODE,SELECT_DATA_FAILED.getCode());
            resultMap.put(MSG,SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;
    }





    /**
     * @Author: js.zhang
     * @Description: 查询 条件  分页
     * @DateTime: 2020/7/16 10:18
     * @Params: [hashMap, redisService]
     * @Return java.util.Map<java.lang.String, java.lang.Object>
     */

    public Map<String, Object> selectAllUser(HashMap hashMap, RedisService redisService) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //获取当前用户的token令牌
//        String tokenval = redisService.getOne("tokenId").toString();
//        //检测token
//        if (null == tokenval) {
//            resultMap.put(CODE, LOGIN_TIMEOUT_EXIT.getCode());
//            resultMap.put(MSG, LOGIN_TIMEOUT_EXIT.getMsg());
//            return resultMap;
//        }

        if (hashMap.size() > 0) {
            //使用pageHelper
            PageHelper.startPage(BaseUtil.transToInt(hashMap.get("pageNo")), BaseUtil.transToInt(hashMap.get("pageSize")));
            //首先体哦见查询
            List<HashMap> list = userMapper.selectUserAll(hashMap);
            PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(list);
            if (null != pageInfo && pageInfo.getSize() > 0) {
                resultMap.put(CODE, SUCCESS.getCode());
                resultMap.put(MSG, SUCCESS.getMsg());
                resultMap.put(DATA, pageInfo);
            } else {
                resultMap.put(CODE, FAILED.getCode());
                resultMap.put(MSG, FAILED.getMsg());
            }

        }
        return resultMap;

    }


    /**
    * @Author: js.zhang
    * @Description: 模糊查询username
    * @DateTime: 2020/7/20 20:14
    * @Params: [user]
    * @Return java.util.List<com.aaa.model.User>
    */
    public List<User> selectUserAll(User user){
        //获取user全部属性
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user.getDeptId()!=null||user.getUsername()!=null){
            //拼接条件模糊查询username和精确查询deptid
            criteria.andLike("username","%"+user.getUsername()+"%").andEqualTo("deptId",user.getDeptId());

            List<User> userList = userMapper.selectByExample(example);
            return userList;
        }else {
            List<User> userList = userMapper.selectAll();
            return  userList;
        }



    }


}

