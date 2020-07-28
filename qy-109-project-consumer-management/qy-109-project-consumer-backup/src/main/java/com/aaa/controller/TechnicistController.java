package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Technicist;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultData selectTechById(Long id){
     return    iProjectService.selectTechById(id);
    }


    /**
     * @Author: js.zhang
     * @Description: 通过userId查询技术人员列表
     * @DateTime: 2020/7/18 14:34
     * @Params: [technicist, pageNo, pageSize]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectTechByUserid")
    public ResultData selectTechByUserid(Technicist technicist, Integer pageNo, Integer pageSize){
        return    iProjectService.selectTechByUserid(technicist,pageNo,pageSize);
    }


    /**
     * @Author: js.zhang
     * @Description: 修改技术人员信息
     * @DateTime: 2020/7/20 11:22
     * @Params: [technicist]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(     Technicist technicist){
        return    iProjectService.updateTechnicist(technicist);
    }

    /**
     * @Author: js.zhang
     * @Description: 新增技术人员信息
     * @DateTime: 2020/7/20 11:24
     * @Params: [technicist]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("addTechnicist")
    public ResultData addTechnicist(     Technicist technicist){
        return    iProjectService.addTechnicist(technicist);
    }


    /**
     * @Author: js.zhang
     * @Description: 删除技术人员信息
     * @DateTime: 2020/7/20 11:45
     * @Params: [technicist]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/deleteTechnicist")
    public ResultData deleteTechnicist(     Technicist technicist){
        return    iProjectService.deleteTechnicist(technicist);
    }

}
