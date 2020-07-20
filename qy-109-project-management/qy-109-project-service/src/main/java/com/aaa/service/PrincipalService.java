package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.PrincipalMapper;
import com.aaa.model.Principal;
import com.aaa.utils.BaseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.OperationStatus.FAILED;
import static com.aaa.status.OperationStatus.SUCCESS;
import static com.aaa.status.SelectStatus.*;

/**
 * @program: qy-109-project
 * @description: 单位负责人
 * @author: 张竞赛
 * @create: 2020-07-18 09:47
 **/
@Service
public class PrincipalService extends BaseService<Principal> {
    @Autowired
    private PrincipalMapper principalMapper;

        /**
        * @Author: js.zhang
        * @Description: 通过userId查询法人信息列表
        * @DateTime: 2020/7/18 14:13
        * @Params: [principal, pageNo, pageSize]
        * @Return java.util.Map<java.lang.String,java.lang.Object>
        */
    public Map<String,Object> selectPrincipalByUserId(Map map){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //对象转数字
        Integer pageNo = BaseUtil.transToInt(map.get("pageNo"));
        Integer pageSize = BaseUtil.transToInt(map.get("pageSize"));
        Long userId = BaseUtil.transToLong(map.get("userId"));
        //  Long userId = Long.valueOf(map.get("userId").toString());
        Principal principal = new Principal().setUserId(userId);


        PageInfo<Principal> principalPageInfo = super.selectListByPage(principal, pageNo, pageSize);

        if (null != principalPageInfo  && principalPageInfo.getSize() > 0) {
            resultMap.put(CODE, SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG, SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA, principalPageInfo);
        } else {
            resultMap.put(CODE,  SELECT_DATA_FAILED.getCode());
            resultMap.put(MSG,  SELECT_DATA_FAILED.getMsg());
        }
            return resultMap;

    }



    /**
    * @Author: js.zhang
    * @Description: 通过id查询法人表个人信息
    * @DateTime: 2020/7/18 10:12
    * @Params: [userId]
    * @Return java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String,Object> selectPrincipalById(Long id){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Principal principal = principalMapper.selectPrincipalById(id);
        if (principal != null && !"".equals(principal)){
            resultMap.put(CODE,SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG,SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA,principal);
        }else{
            resultMap.put(CODE,SELECT_DATA_FAILED.getCode());
            resultMap.put(MSG,SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;


    }


}
