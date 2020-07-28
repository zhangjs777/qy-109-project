package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Principal;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-28 14:28
 **/
@RestController
public class PrincipalController {
    @Autowired
    private IProjectService iProjectService;
    /**
     * @Author: js.zhang
     * @Description: 通过id查询法人个人信息
     * @DateTime: 2020/7/18 10:23
     * @Params: [userId]
     * @Return com.aaa.base.ResultData
     */
    @GetMapping("/selectPrincipalById")
    ResultData selectPrincipalByUserId(Long id){
        return iProjectService.selectPrincipalByUserId(id);
    }


    /**
     * @Author: js.zhang
     * @Description: 通过userId查询法人信息列表
     * @DateTime: 2020/7/18 11:44
     * @Params: [principal, pageNo, pageSize]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectPrincipalByUserId")
    ResultData selectPrincipalByUserId(@RequestBody Principal principal){
        return iProjectService.selectPrincipalByUserId(principal);
    }

}
