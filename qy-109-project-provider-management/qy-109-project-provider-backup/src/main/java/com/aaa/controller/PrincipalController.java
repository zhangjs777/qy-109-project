package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Principal;
import com.aaa.service.PrincipalService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.CODE;
import static com.aaa.status.OperationStatus.DATA_NOT_EXIST;
import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @program: qy-109-project
 * @description:  查询单位法人信息
 * @author: 张竞赛
 * @create: 2020-07-18 10:17
 **/
@RestController
public class PrincipalController extends CommonController<Principal> {

    @Autowired
    private PrincipalService principalService;

    @Override
    public BaseService<Principal> getBaseService() {
        return principalService;
    }

    /**
    * @Author: js.zhang
    * @Description: 通过id查询法人个人信息
    * @DateTime: 2020/7/18 10:23
    * @Params: [userId]
    * @Return com.aaa.base.ResultData
    */
    @GetMapping("/selectPrincipalById")
    public ResultData selectPrincipalByUserId(String id){
        long l = Long.parseLong(id);
        //执行查询语句
            Map<String, Object> stringObjectMap = principalService.selectPrincipalById(l);

            if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))) {
                return super.operationSuccess(stringObjectMap);
            } else if (SELECT_DATA_FAILED.getCode().equals(stringObjectMap.get(CODE))) {
                return super.operationFailed();
            } else {
                return super.operationFailed(DATA_NOT_EXIST.getMsg());
            }

    }

            /**
            * @Author: js.zhang
            * @Description: 通过userId查询法人信息列表
            * @DateTime: 2020/7/18 11:44
            * @Params: [principal, pageNo, pageSize]
            * @Return com.aaa.base.ResultData
            */    
        @PostMapping("/selectPrincipalByUserId")
        public ResultData selectPrincipalByUserId(@RequestBody Map map){
        Map<String, Object> stringObjectMap = principalService.selectPrincipalByUserId(map);
        
                if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
                    return super.operationSuccess(stringObjectMap);
                }else if (SELECT_DATA_FAILED.getCode().equals(stringObjectMap.get(CODE))){
                    return super.operationFailed();
                }else{
                    return super.operationFailed(DATA_NOT_EXIST.getMsg());
                }

    }
    @PostMapping("/addPrincipal")
    public ResultData addPrincipal(@RequestBody Principal principal){
            principal.setCreateTime(new Date());
        Integer integer = getBaseService().add(principal);
        if (integer>0){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }

    }





}
