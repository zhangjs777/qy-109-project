package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Audit;
import com.aaa.service.AuditService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.OperationStatus.*;

/**
 * @program: qy-109-project
 * @description: 审核表
 * @author: 张竞赛
 * @create: 2020-07-17 10:24
 **/
@RestController
public class AuditController extends CommonController<Audit> {
    @Autowired
    private AuditService auditService;

    @Override
    public BaseService getBaseService() {
        return auditService;
    }





    /**
    * @Author: js.zhang
    * @Description: 通过refId查询对应审核信息
    * @DateTime: 2020/7/17 10:47
    * @Params: [refId]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/selectByRefId")
    public ResultData selectByRefId(@RequestBody Audit audit){


        PageInfo<Audit> auditPageInfo = auditService.selectByRefId(audit);

         if (auditPageInfo!=null&&auditPageInfo.getSize()>0){
                   return super.operationSuccess(auditPageInfo);
                }else {
             return super.operationSuccess(auditPageInfo);
         }

    }







}
