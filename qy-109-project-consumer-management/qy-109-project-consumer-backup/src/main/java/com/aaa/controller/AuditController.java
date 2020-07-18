package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-17 11:07
 **/
@RestController
public class AuditController {

    @Autowired
    private IProjectService iProjectService;
    /**
     * @Author: js.zhang
     * @Description: 项目通过id对应审核表refId查询审核记录
     * @DateTime: 2020/7/17 11:04
     * @Params: [refId, pageNo, pageSize]
     * @Return com.aaa.base.ResultData
     */

}
