package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-23 19:26
 **/
@RestController
public class TechnicistController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @Author: js.zhang
     * @Description: 通过id查询技术人员个人信息
     * @DateTime: 2020/7/18 15:19
     * @Params: [id]
     * @Return com.aaa.base.ResultData
     */
    @GetMapping("/selectTechById")
    public ResultData selectTechByUserId(Long id){
     return    iProjectService.selectTechByUserId(id);
    }
}
