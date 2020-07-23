package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Audit;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @Description: 通过refId查询对应审核信息
     * @DateTime: 2020/7/17 10:47
     * @Params: [refId]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectByRefId")
    public ResultData selectByRefId( Audit audit){
      return   iProjectService.selectByRefId(audit);
    }

}
